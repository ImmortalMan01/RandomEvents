package com.immortalman01.randomevents.listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.api.events.ReventSpawnEvent;
import com.immortalman01.randomevents.match.Kit;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.MatchActive;
import com.immortalman01.randomevents.util.Constantes;
import com.immortalman01.randomevents.util.InventoryUtils;
import com.immortalman01.randomevents.util.UtilsRandomEvents;
import com.immortalman01.randomevents.util.UtilsSQL;
import com.immortalman01.randomevents.util.RandomEventsHolder;
import com.immortalman01.util.enums.XMaterial;
import com.immortalman01.util.enums.XSound;

public class GUI implements Listener {

	private RandomEvents plugin;

	public GUI(RandomEvents plugin) {
		this.plugin = plugin;
	}

       @EventHandler(priority = EventPriority.HIGHEST)
       public void onInventoryClick(InventoryClickEvent event) {

		if (event.getWhoClicked() instanceof Player) {
			try {
				Player p = (Player) event.getWhoClicked();
				if (event.getCurrentItem() != null) {
					if (plugin.getMatchActive() != null
							&& plugin.getMatchActive().getPlayerHandler().getPlayers().contains(p.getName())) {
						if ( event.getSlotType() != null && (event.getSlotType() == InventoryType.SlotType.ARMOR)) {
							switch(plugin.getMatchActive().getMatch().getMinigame()){
							case PAINTBALL:
							case PAINTBALL_TOP_KILL:
							case SPLATOON:
							case BATTLE_ROYALE_TEAMS:
							case TOP_KILLER_TEAMS:
							case TSW_REAL:
							case TSG_REAL:
							case HOEHOEHOE:
								event.setCancelled(true);
								break;
							default:
								break;
                }

        }

					}
				}
			} catch (Throwable e) {
				plugin.getLoggerP().info(e.toString());
			}
		}

               Inventory topInventory = event.getView().getTopInventory();
               RandomEventsHolder holder = null;
               if (topInventory != null && topInventory.getHolder() instanceof RandomEventsHolder) {
                       holder = (RandomEventsHolder) topInventory.getHolder();
               } else if (topInventory != null) {
                       // Some servers clone the inventory for each click and lose the holder
                       String title = event.getView().getTitle();
                       if (title != null) {
                               if (title.equals(plugin.getLanguage().getStatsGuiName())) {
                                       holder = new RandomEventsHolder(RandomEventsHolder.GuiType.STATS);
                               } else if (title.equals(plugin.getLanguage().getCreditsGuiName())) {
                                       holder = new RandomEventsHolder(RandomEventsHolder.GuiType.CREDITS);
                               } else if (title.equals(plugin.getLanguage().getKitGuiName())) {
                                       holder = new RandomEventsHolder(RandomEventsHolder.GuiType.KITS);
                               } else if (title.equals(plugin.getLanguage().getTeamGuiName())) {
                                       holder = new RandomEventsHolder(RandomEventsHolder.GuiType.TEAMS);
                               }
                       }
               }

               if (holder != null) {
                       // Cancel any attempts to move items from or to the menu.
                       // Using isShiftClick covers quick move actions while clickedTopInventory handles normal clicks.
                       if (clickedTopInventory(event) || event.isShiftClick()) {
                               event.setCancelled(true);
                       }

                       switch (holder.getType()) {
                       case STATS:
                               if (clickedTopInventory(event)) {
                                       event.setCancelled(true);
                               }
                               break;
                       case CREDITS:
                               useCreditsGui(event);
                               break;
                       case KITS:
                               useKitGUI(event);
                               break;
                       case TEAMS:
                               useTeamGUI(event);
                               break;
                       default:
                               break;
                       }
               }
       }

       @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
       public void onInventoryDrag(InventoryDragEvent event) {
               Inventory topInventory = event.getView().getTopInventory();
               if (topInventory != null && topInventory.getHolder() instanceof RandomEventsHolder) {
                       event.setCancelled(true);
               }
        }

        private void useCreditsGui(InventoryClickEvent event) {

                if (event.getWhoClicked() instanceof Player) {
                        if (clickedTopInventory(event)) {
                                event.setCancelled(true);
                        }
                        try {
				Player p = (Player) event.getWhoClicked();
				if (event.getCurrentItem() != null) {
					if (plugin.getMatchActive() != null
							&& plugin.getMatchActive().getPlayerHandler().getPlayers().contains(p.getName())) {
						if (event.getSlotType() != null && (event.getSlotType() == InventoryType.SlotType.ARMOR)) {
							event.setCancelled(true);
						}
					}

					if (event.getCurrentItem().getType() != null
							&& event.getCurrentItem().getType().equals(XMaterial.OAK_SIGN.parseMaterial())) {
						String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
						Integer page = Integer
								.valueOf(itemName.split(plugin.getLanguage().getCreditsGuiPage())[1].trim());
						UtilsSQL.getCreditsGUI(p, page, plugin);
					} else {
						ItemStack item = event.getCurrentItem();
						if (item.hasItemMeta()) {
							ItemMeta itemMeta = item.getItemMeta();
							String itemName = itemMeta.getDisplayName();
							if (p.hasPermission(Constantes.PERM_COOLDOWN_BYPASS)) {
								if (plugin.getMatchActive() != null) {
									p.sendMessage(plugin.getLanguage().getTagPlugin()
											+ plugin.getLanguage().getAlreadyMatch());

								} else {
									Match m = UtilsRandomEvents.searchEvent(ChatColor.stripColor(itemName), plugin);
									if (m != null) {
										plugin.setForzado(Boolean.TRUE);
										plugin.setMatchActive(new MatchActive(m, plugin, true));
										try {
											Bukkit.getPluginManager().callEvent(new ReventSpawnEvent(plugin.getMatchActive(),true));
										} catch (Exception e) {
											plugin.getLoggerP().info("[RandomEvents] WARN :: Couldnt fire the ReventSpawnEvent.");
										}
										p.closeInventory();

									}

								}
							} else if (p.hasPermission(Constantes.PERM_COOLDOWN)) {
								if (plugin.getMatchActive() != null) {
									p.sendMessage(plugin.getLanguage().getTagPlugin()
											+ plugin.getLanguage().getAlreadyMatch());

								} else {

									if (plugin.getCooldowns().containsKey(p.getName())) {
										if (plugin.getCooldowns().get(p.getName()).before(new Date())) {
											plugin.getCooldowns().remove(p.getName());
											Match m = UtilsRandomEvents.searchEvent(ChatColor.stripColor(itemName),
													plugin);
											if (m != null) {
												plugin.setForzado(Boolean.TRUE);
												plugin.setMatchActive(new MatchActive(m, plugin, true));
												try {
													Bukkit.getPluginManager().callEvent(new ReventSpawnEvent(plugin.getMatchActive(),true));
												} catch (Exception e) {
													plugin.getLoggerP().info("[RandomEvents] WARN :: Couldnt fire the ReventSpawnEvent.");
												}
												p.closeInventory();
												Calendar c = Calendar.getInstance();
												c.setTime(new Date());
												c.add(Calendar.SECOND, plugin.getReventConfig().getCooldownUsersBeginEvents());
												plugin.getCooldowns().put(p.getName(), c.getTime());

											}

										} else {
											if (plugin.getReventConfig().isMysqlEnabled()) {
												String credits = itemMeta.getLore().get(0);
												if (credits.equals(plugin.getLanguage().getCreditsCooldown())) {
													Date now = new Date();
													Date cooldown = plugin.getCooldowns().get(p.getName());
													p.sendMessage(plugin.getLanguage().getTagPlugin()
															+ plugin.getLanguage().getCreditsCooldown() + " "
															+ UtilsRandomEvents.calculateTimeHoursPoints(
																	(cooldown.getTime() - now.getTime()) / 1000));
												} else {
													String languageCredits = plugin.getLanguage().getCreditsBal();
													languageCredits = ChatColor.stripColor(languageCredits);
													credits = ChatColor.stripColor(credits);
													String[] splitLanguage = languageCredits.split("%credits%");

													String aux = "";
													if (splitLanguage[0] != null && !splitLanguage[0].isEmpty()) {
														aux = credits.split(splitLanguage[0])[1];
														if (aux == null || aux.isEmpty()) {
															credits = credits.split(splitLanguage[0])[0];
														} else {
															credits = aux;

														}
													}
													if (splitLanguage.length > 1 && splitLanguage[1] != null
															&& !splitLanguage[1].isEmpty()) {
														aux = credits.split(splitLanguage[1])[0];

														if (aux == null || aux.isEmpty()) {
															credits = credits.split(splitLanguage[1])[1];
														} else {
															credits = aux;

														}
													}

													Integer amount = Integer.valueOf(credits);
													if (amount > 0) {
														Match m = UtilsRandomEvents
																.searchEvent(ChatColor.stripColor(itemName), plugin);
														if (m != null) {
															UtilsSQL.removeCredits(p, m.getName(), plugin);
															plugin.setForzado(Boolean.TRUE);
															plugin.setMatchActive(new MatchActive(m, plugin, true));
															try {
																Bukkit.getPluginManager().callEvent(new ReventSpawnEvent(plugin.getMatchActive(),true));
															} catch (Exception e) {
																plugin.getLoggerP().info("[RandomEvents] WARN :: Couldnt fire the ReventSpawnEvent.");
															}
															p.closeInventory();
														}
													}
												}
											} else {
												Date now = new Date();
												Date cooldown = plugin.getCooldowns().get(p.getName());
												p.sendMessage(plugin.getLanguage().getTagPlugin()
														+ plugin.getLanguage().getCreditsCooldown() + " "
														+ UtilsRandomEvents.calculateTimeHoursPoints(
																(cooldown.getTime() - now.getTime()) / 1000));
											}
										}

									} else {
										Match m = UtilsRandomEvents.searchEvent(ChatColor.stripColor(itemName), plugin);
										if (m != null) {
											plugin.setForzado(Boolean.TRUE);
											plugin.setMatchActive(new MatchActive(m, plugin, true));
											try {
												Bukkit.getPluginManager().callEvent(new ReventSpawnEvent(plugin.getMatchActive(),true));
											} catch (Exception e) {
												plugin.getLoggerP().info("[RandomEvents] WARN :: Couldnt fire the ReventSpawnEvent.");
											}
											p.closeInventory();
											Calendar c = Calendar.getInstance();
											c.setTime(new Date());
											c.add(Calendar.SECOND, plugin.getReventConfig().getCooldownUsersBeginEvents());
											plugin.getCooldowns().put(p.getName(), c.getTime());
										}
									}

								}
							} else if (plugin.getReventConfig().isMysqlEnabled()) {
								String credits = itemMeta.getLore().get(0);
								String languageCredits = plugin.getLanguage().getCreditsBal();
								languageCredits = ChatColor.stripColor(languageCredits);
								credits = ChatColor.stripColor(credits);
								String[] splitLanguage = languageCredits.split("%credits%");

								String aux = "";
								if (splitLanguage[0] != null && !splitLanguage[0].isEmpty()) {
									aux = credits.split(splitLanguage[0])[1];
									if (aux == null || aux.isEmpty()) {
										credits = credits.split(splitLanguage[0])[0];
									} else {
										credits = aux;

									}
								}
								if (splitLanguage.length > 1 && splitLanguage[1] != null
										&& !splitLanguage[1].isEmpty()) {
									aux = credits.split(splitLanguage[1])[0];

									if (aux == null || aux.isEmpty()) {
										credits = credits.split(splitLanguage[1])[1];
									} else {
										credits = aux;

									}
								}

								Integer amount = Integer.valueOf(credits);
								if (amount > 0) {
									Match m = UtilsRandomEvents.searchEvent(ChatColor.stripColor(itemName), plugin);
									if (m != null) {
										UtilsSQL.removeCredits(p, m.getName(), plugin);
										plugin.setForzado(Boolean.TRUE);
										plugin.setMatchActive(new MatchActive(m, plugin, true));
										try {
											Bukkit.getPluginManager().callEvent(new ReventSpawnEvent(plugin.getMatchActive(),true));
										} catch (Exception e) {
											plugin.getLoggerP().info("[RandomEvents] WARN :: Couldnt fire the ReventSpawnEvent.");
										}
										p.closeInventory();
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				plugin.getLoggerP().info(e.toString());
			}
		}

	}

	private void useKitGUI(InventoryClickEvent event) {

                if (event.getWhoClicked() instanceof Player) {

                        if (clickedTopInventory(event)) {
                                event.setCancelled(true);
                        }
			try {
				Player p = (Player) event.getWhoClicked();

				if (event.getCurrentItem() != null) {

					if (event.getCurrentItem().getType() != null
							&& event.getCurrentItem().getType().equals(XMaterial.OAK_SIGN.parseMaterial())) {

						String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
						Integer page = Integer
								.valueOf(itemName.split(plugin.getLanguage().getCreditsGuiPage())[1].trim());
						UtilsRandomEvents.createGUIKits(p, page, plugin, plugin.getMatchActive());
					} else {

						ItemStack item = event.getCurrentItem();
						if (item.hasItemMeta()) {

                                                        List<Kit> kits = UtilsRandomEvents.kitsAvailable(p,
                                                                        plugin.getMatchActive().getMatch().getKits(), plugin);
                                                        // Using Inventory#first again caused the wrong kit to be
                                                        // chosen when multiple identical icons were present. Use
                                                        // the slot the player actually clicked so the correct kit
                                                        // is selected regardless of menu layout.
                                                        Integer pos = event.getSlot();

							if (pos < kits.size()) {
								Kit kit = kits.get(pos);
								plugin.getMatchActive().getPlayerHandler().getPlayerKits().put(p, kit);
								p.sendMessage(plugin.getLanguage().getTagPlugin()
										+ plugin.getLanguage().getKitChosen().replaceAll("%kit_name%", kit.getName()));
								p.closeInventory();
								UtilsRandomEvents.playSound(plugin,p, XSound.ENTITY_PLAYER_LEVELUP);
							}
						}
					}
				}
			} catch (Exception e) {
				plugin.getLoggerP().info(e.toString());
			}
		}

	}

       private void useTeamGUI(InventoryClickEvent event) {

               if (!(event.getWhoClicked() instanceof Player)) {
                       return;
               }

               if (!clickedTopInventory(event)) {
                       return;
               }

               event.setCancelled(true);

               try {
                       Player p = (Player) event.getWhoClicked();

                       if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) {
                               return;
                       }

                       MatchActive active = plugin.getMatchActive();
                       if (active == null) {
                               return;
                       }

                    int topSize = event.getView().getTopInventory().getSize();
                    // Use raw slot to handle modern server versions where
                    // InventoryClickEvent#getSlot() may be relative to the
                    // clicked inventory. Raw slot is always the index within
                    // the open view so it correctly maps to our GUI slots.
                    int slot = event.getRawSlot();
                    if (slot < 0 || slot >= topSize) {
                            return;
                    }

                    ItemStack icon = event.getCurrentItem();
                    int teamCount = active.getMatch().getNumberOfTeams();
                    String display = null;
                    if (icon.hasItemMeta() && icon.getItemMeta().hasDisplayName()) {
                            display = icon.getItemMeta().getDisplayName();
                    }

                    Integer pos = UtilsRandomEvents.teamIndexFromName(display);
                    if (pos == null || pos < 0 || pos >= teamCount) {
                            // Fall back to slot position if name lookup failed
                            pos = slot;
                            if (pos < 0 || pos >= teamCount) {
                                    return;
                            }
                    }

                       Integer equipoActual = active.getEquipo(p);

                       if (equipoActual != null) {
                               Set<Player> old = active.getPlayerHandler().getEquipos().get(equipoActual);
                               Set<Player> oldCopy = active.getPlayerHandler().getTeamsCopy().get(equipoActual);
                               if (old != null) {
                                       old.remove(p);
                               }
                               if (oldCopy != null) {
                                       oldCopy.remove(p);
                               }
                       }

                       if (active.getPlayerHandler().getEquipos().containsKey(pos)) {
                               active.getPlayerHandler().getEquipos().get(pos).add(p);
                               active.getPlayerHandler().getTeamsCopy().get(pos).add(p);
                       } else {
                               active.getPlayerHandler().getEquipos().put(pos, new HashSet<Player>());
                               active.getPlayerHandler().getTeamsCopy().put(pos, new HashSet<Player>());
                               active.getPlayerHandler().getEquipos().get(pos).add(p);
                               active.getPlayerHandler().getTeamsCopy().get(pos).add(p);
                       }

                       p.closeInventory();
                       UtilsRandomEvents.playSound(plugin, p, XSound.ENTITY_PLAYER_LEVELUP);
                       active.applyTeamSelection(p);
                       active.updateScoreboards();

               } catch (Exception e) {
                       plugin.getLoggerP().info(e.toString());
               }

       }

        /**
         * Check if the clicked inventory is the top inventory of the current view.
         * This is used to ensure players cannot take items from custom GUIs
         * while still allowing normal interaction with their own inventory.
         */
       /**
        * Determine if the clicked slot belongs to the top inventory of the open
        * view. Previous logic relied on comparing the clicked inventory instance
        * with the view's top inventory which fails on modern server versions
        * where a new inventory instance is created for each click. Using the raw
        * slot index is a reliable way to check if the click occurred in the GUI
        * itself and therefore should be cancelled.
        */
       private boolean clickedTopInventory(InventoryClickEvent event) {
               if (event == null || event.getView() == null) {
                       return false;
               }
               return event.getRawSlot() < event.getView().getTopInventory().getSize();
       }

        public RandomEvents getPlugin() {
                return plugin;
        }

	public void setPlugin(RandomEvents plugin) {
		this.plugin = plugin;
	}

}
