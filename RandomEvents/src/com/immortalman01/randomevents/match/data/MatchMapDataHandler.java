package com.immortalman01.randomevents.match.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.block.data.BlockData;

import com.immortalman01.randomevents.match.utils.Cuboid;

public class MatchMapDataHandler {

	private Map<String, Location> checkpoints;

	private List<Location> chests;

	private Cuboid cuboid;

	private Cuboid actualCuboid;

	private Map<Location, Long> blockDisappear;

        private Map<Location, BlockData> blockDisappeared;

	private Map<Location, Material> blockDisappearedType;

        private Map<Location, BlockData> blockPlaced;

        private Set<Fireball> fireballs;

        private List<Location> blockPartyBlocks;

        /**
         * Snapshot of the blocks inside the map region when the match starts. This
         * allows restoring the map even if some changes were not tracked during the
         * game (e.g. explosions or other events). It is a simple location to
         * BlockData mapping.
         */
        private Map<Location, BlockData> originalBlocks;

	private List<Location> locationsGlasses;
	
	private List<List<Location>> locationsPlatforms;

        public MatchMapDataHandler() {
                super();
                this.blockDisappear = new HashMap<Location, Long>();
                this.blockDisappeared = new HashMap<Location, BlockData>();
                this.blockDisappearedType = new HashMap<Location, Material>();
                this.blockPlaced = new HashMap<Location, BlockData>();
                this.checkpoints = new HashMap<String, Location>();
                this.chests = new ArrayList<Location>();
                this.fireballs = new HashSet<Fireball>();
                this.blockPartyBlocks = new ArrayList<Location>();
                this.locationsGlasses = new ArrayList<Location>();
                this.locationsPlatforms = new ArrayList<>();
                this.originalBlocks = new HashMap<Location, BlockData>();

        }

	public List<List<Location>> getLocationsPlatforms() {
		return locationsPlatforms;
	}

	public void setLocationsPlatforms(List<List<Location>> locationsPlatforms) {
		this.locationsPlatforms = locationsPlatforms;
	}

	public List<Location> getLocationsGlasses() {
		return locationsGlasses;
	}

	public void setLocationsGlasses(List<Location> locationsGlasses) {
		this.locationsGlasses = locationsGlasses;
	}

	public Set<Fireball> getFireballs() {
		return fireballs;
	}

	public void setFireballs(Set<Fireball> fireballs) {
		this.fireballs = fireballs;
	}

	public Map<String, Location> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(Map<String, Location> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public List<Location> getChests() {
		return chests;
	}

	public void setChests(List<Location> chests) {
		this.chests = chests;
	}

	public Cuboid getCuboid() {
		return cuboid;
	}

	public void setCuboid(Cuboid cuboid) {
		this.cuboid = cuboid;
	}

	public Cuboid getActualCuboid() {
		return actualCuboid;
	}

	public void setActualCuboid(Cuboid actualCuboid) {
		this.actualCuboid = actualCuboid;
	}

	public Map<Location, Long> getBlockDisappear() {
		return blockDisappear;
	}

	public void setBlockDisappear(Map<Location, Long> blockDisappear) {
		this.blockDisappear = blockDisappear;
	}

        public Map<Location, BlockData> getBlockDisappeared() {
                return blockDisappeared;
        }

        public void setBlockDisappeared(Map<Location, BlockData> blockDisappeared) {
                this.blockDisappeared = blockDisappeared;
        }

	public Map<Location, Material> getBlockDisappearedType() {
		return blockDisappearedType;
	}

	public void setBlockDisappearedType(Map<Location, Material> blockDisappearedType) {
		this.blockDisappearedType = blockDisappearedType;
	}

        public Map<Location, BlockData> getBlockPlaced() {
                return blockPlaced;
        }

        public void setBlockPlaced(Map<Location, BlockData> blockPlaced) {
                this.blockPlaced = blockPlaced;
        }

	public void setBlockPartyBlocks(List<Location> blocksLocation) {
		this.blockPartyBlocks = new ArrayList<Location>(blocksLocation);

	}

        public List<Location> getBlockPartyBlocks() {
                return blockPartyBlocks;
        }

        public Map<Location, BlockData> getOriginalBlocks() {
                return originalBlocks;
        }

        public void setOriginalBlocks(Map<Location, BlockData> originalBlocks) {
                this.originalBlocks = originalBlocks;
        }

        /**
         * Capture a snapshot of all blocks inside the current cuboid region. This
         * should be invoked when the match starts before any blocks are modified.
         */
        public void captureOriginalState() {
                if (cuboid == null) {
                        return;
                }
                originalBlocks.clear();
                for (int x = (int) Math.floor(cuboid.getMinX()); x <= cuboid.getMaxX(); x++) {
                        for (int y = (int) Math.floor(cuboid.getMinY()); y <= cuboid.getMaxY(); y++) {
                                for (int z = (int) Math.floor(cuboid.getMinZ()); z <= cuboid.getMaxZ(); z++) {
                                        Location l = new Location(cuboid.getWorld(), x, y, z);
                                        originalBlocks.put(l, l.getBlock().getBlockData().clone());
                                }
                        }
                }
        }

        /**
         * Restore all blocks captured by {@link #captureOriginalState()} back into the
         * world.
         */
        public void restoreOriginalState() {
                for (Map.Entry<Location, BlockData> e : originalBlocks.entrySet()) {
                        e.getKey().getBlock().setBlockData(e.getValue());
                }
                originalBlocks.clear();
        }

}
