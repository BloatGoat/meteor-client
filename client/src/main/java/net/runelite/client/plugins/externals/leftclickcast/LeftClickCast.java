/*
 * Copyright (c) 2019-2020, ganom <https://github.com/Ganom>
 * All rights reserved.
 * Licensed under GPL3, see LICENSE for the full scope.
 */
package net.runelite.client.plugins.externals.leftclickcast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

import eventbus.events.ConfigChanged;
import eventbus.events.GameStateChanged;
import eventbus.events.ItemContainerChanged;
import eventbus.events.MenuEntryAdded;
import meteor.Logger;
import meteor.Main;
import meteor.input.KeyManager;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.rs.ClientThread;
import meteor.util.HotkeyListener;
import meteor.util.WeaponMap;
import meteor.util.WeaponStyle;
import net.runelite.api.*;
import net.runelite.api.util.Text;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import meteor.util.PvPUtil;

import static net.runelite.api.MenuAction.*;

@PluginDescriptor(
	name = "Left Click Cast",
	description = "Casting made even easier.",
	tags = "ganom"
)
@SuppressWarnings("unused")
public class LeftClickCast extends Plugin
{
	private Client client = Main.client;

	private LeftClickConfig config = configuration(LeftClickConfig.class);

	private KeyManager keyManager = KeyManager.INSTANCE;

	private ClientThread clientThread = ClientThread.INSTANCE;

	private final Set<Integer> whitelist = new HashSet<>();

	private Logger log = new Logger("LeftClickCast");

	private boolean isMage;
	private boolean disabled = false;
	private Spells currentSpell = config.spellOne();

	private final HotkeyListener spellOneSwap = new HotkeyListener(() -> config.spellOneSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellOne();
		}
	};

	private final HotkeyListener spellTwoSwap = new HotkeyListener(() -> config.spellTwoSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellTwo();
		}
	};

	private final HotkeyListener spellThreeSwap = new HotkeyListener(() -> config.spellThreeSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellThree();
		}
	};

	private final HotkeyListener spellFourSwap = new HotkeyListener(() -> config.spellFourSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellFour();
		}
	};

	private final HotkeyListener spellFiveSwap = new HotkeyListener(() -> config.spellFiveSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellFive();
		}
	};

	private final HotkeyListener spellSixSwap = new HotkeyListener(() -> config.spellSixSwap())
	{
		@Override
		public void hotkeyPressed()
		{
			currentSpell = config.spellSix();
		}
	};

	private final HotkeyListener disable = new HotkeyListener(() -> config.disable())
	{
		@Override
		public void hotkeyPressed()
		{
			disabled = !disabled;
			clientThread.invoke(() ->
				client.addChatMessage(
					ChatMessageType.BROADCAST,
					"",
					"LeftClickCast has been " + (disabled ? "disabled." : "enabled."),
					""
				)
			);
		}
	};

	@Override
	public void onStart()
	{
		if (client.getGameState() == GameState.LOGGED_IN)
		{
			keyManager.registerKeyListener(spellOneSwap, getClass());
			keyManager.registerKeyListener(spellTwoSwap, getClass());
			keyManager.registerKeyListener(spellThreeSwap, getClass());
			keyManager.registerKeyListener(spellFourSwap, getClass());
			keyManager.registerKeyListener(spellFiveSwap, getClass());
			keyManager.registerKeyListener(spellSixSwap, getClass());
			keyManager.registerKeyListener(disable, getClass());
		}
		updateConfig();
	}

	@Override
	public void onStop()
	{
		keyManager.unregisterKeyListener(spellOneSwap);
		keyManager.unregisterKeyListener(spellTwoSwap);
		keyManager.unregisterKeyListener(spellThreeSwap);
		keyManager.unregisterKeyListener(spellFourSwap);
		keyManager.unregisterKeyListener(spellFiveSwap);
		keyManager.unregisterKeyListener(spellSixSwap);
		keyManager.unregisterKeyListener(disable);
	}

	@Override
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() != GameState.LOGGED_IN)
		{
			keyManager.unregisterKeyListener(spellOneSwap);
			keyManager.unregisterKeyListener(spellTwoSwap);
			keyManager.unregisterKeyListener(spellThreeSwap);
			keyManager.unregisterKeyListener(spellFourSwap);
			keyManager.unregisterKeyListener(spellFiveSwap);
			keyManager.unregisterKeyListener(spellSixSwap);
			keyManager.unregisterKeyListener(disable);
			return;
		}
		keyManager.registerKeyListener(spellOneSwap, getClass());
		keyManager.registerKeyListener(spellTwoSwap, getClass());
		keyManager.registerKeyListener(spellThreeSwap, getClass());
		keyManager.registerKeyListener(spellFourSwap, getClass());
		keyManager.registerKeyListener(spellFiveSwap, getClass());
		keyManager.registerKeyListener(spellSixSwap, getClass());
		keyManager.registerKeyListener(disable, getClass());
	}

	@Override
	public void onConfigChanged(ConfigChanged event)
	{
		updateConfig();
	}

	@Override
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		if (disabled)
		{
			return;
		}

		if (client.isMenuOpen())
		{
			return;
		}

		if (event.getForceLeftClick())
		{
			return;
		}

		if (event.getOpcode() == MenuAction.PLAYER_SECOND_OPTION.getId() && isMage)
		{
			final String name = Text.standardize(event.getTarget(), true);

			if (!config.disableFriendlyRegionChecks() && (client.getVarbitValue(5314) == 0 && (client.isFriended(name, false))))
			{
				return;
			}

			if (!config.disableFriendlyRegionChecks())
			{
				try
				{
					boolean b = (!PvPUtil.isAttackable(client.getCachedPlayers()[event.getIdentifier()]));
				}
				catch (IndexOutOfBoundsException ex)
				{
					return;
				}
			}

			setSelectSpell(currentSpell.getSpell());
			client.createMenuEntry(client.getMenuOptionCount())
				.setOption("(P) Left Click " + client.getSelectedSpellName() + " -> ")
				.setTarget(event.getTarget())
				.setType(WIDGET_TARGET_ON_PLAYER)
				.setIdentifier(event.getIdentifier())
				.setParam0(0)
				.setParam1(0)
				.setForceLeftClick(true);
		}
		if (event.getOpcode() == MenuAction.NPC_SECOND_OPTION.getId() && isMage)
		{
			try
			{
				NPC npc = validateNpc(event.getIdentifier());

				if (npc == null)
				{
					return;
				}

				if (config.disableStaffChecks())
				{
				isMage = true;
				}

				setSelectSpell(currentSpell.getSpell());
				client.createMenuEntry(client.getMenuOptionCount())
					.setOption("(N) Left Click " + client.getSelectedSpellName() + " -> ")
					.setTarget(event.getTarget())
					.setType(WIDGET_TARGET_ON_NPC)
					.setIdentifier(event.getIdentifier())
					.setParam0(0)
					.setParam1(0)
					.setForceLeftClick(true);
			}
			catch (IndexOutOfBoundsException ignored)
			{
			}
		}
		if (event.getOpcode() == MenuAction.GROUND_ITEM_THIRD_OPTION.getId() && isMage && config.telegrabItems())
		{
			if (config.disableStaffChecks())
			{
				isMage = true;
			}
			setSelectSpell(currentSpell.getSpell());
			client.createMenuEntry(client.getMenuOptionCount())
					.setOption("(I) Left Click " + client.getSelectedSpellName() + " -> ")
					.setTarget(event.getTarget())
					.setType(WIDGET_TARGET_ON_GROUND_ITEM)
					.setIdentifier(event.getIdentifier())
					.setParam0(event.getParam0())
					.setParam1(event.getParam1())
					.setForceLeftClick(true);
		}
	}

	@Override
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		final ItemContainer ic = event.getItemContainer();

		if (client.getItemContainer(InventoryID.EQUIPMENT) != ic)
		{
			return;
		}

		isMage = false;

		for (Item item : ic.getItems())
		{
			if (WeaponMap.getStyleMap().get(item.getId()) == WeaponStyle.MAGIC)
			{
				isMage = true;
				break;
			}
		}

		if (config.disableStaffChecks())
		{
			isMage = true;
		}
	}

	private void updateConfig()
	{
		whitelist.clear();
		if (config.disableStaffChecks())
		{
			List<String> string = Text.fromCSV(config.whitelist());
			for (String s : string)
			{
				try
				{
					whitelist.add(Integer.parseInt(s));
				}
				catch (NumberFormatException ignored)
				{
				}
			}
		}
	}

	private void setSelectSpell(WidgetInfo info)
	{
		Widget widget = client.getWidget(info);
		if (widget == null)
		{
			log.info("Unable to locate spell widget.");
			return;
		}
		client.setSelectedSpellName("<col=00ff00>" + widget.getName() + "</col>");
		client.setSelectedSpellWidget(widget.getId());
		client.setSelectedSpellChildIndex(-1);
	}

	/**
	 * This method is not ideal, as its going to create a ton of junk
	 * but its the most reliable method i've found so far for validating
	 * NPCs on menu events. Another solution would be to use string
	 * comparison, however most users are used to the id concept
	 * so this was the path of least resistance. I'm open to
	 * suggestions however if anyone wants to offer them.
	 * -Ganom
	 *
	 * @param index Menu event index.
	 * @return {@link NPC} object for comparison.
	 */
	@Nullable
	private NPC validateNpc(int index)
	{
		NPC npc = null;

		for (NPC clientNpc : client.getNpcs())
		{
			if (index == clientNpc.getIndex())
			{
				npc = clientNpc;
				break;
			}
		}

		return npc;
	}
}
