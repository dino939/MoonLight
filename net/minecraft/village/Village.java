//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraft.village;

import com.google.common.collect.*;
import net.minecraftforge.event.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import javax.annotation.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import com.mojang.authlib.*;
import net.minecraft.util.math.*;
import net.minecraft.nbt.*;
import net.minecraftforge.common.capabilities.*;

public class Village implements ICapabilitySerializable
{
    private World world;
    private final List villageDoorInfoList;
    private BlockPos centerHelper;
    private BlockPos center;
    private int villageRadius;
    private int lastAddDoorTimestamp;
    private int tickCounter;
    private int numVillagers;
    private int noBreedTicks;
    private final Map playerReputation;
    private final List villageAgressors;
    private int numIronGolems;
    private CapabilityDispatcher capabilities;
    
    public Village() {
        this.villageDoorInfoList = Lists.newArrayList();
        this.centerHelper = BlockPos.ORIGIN;
        this.center = BlockPos.ORIGIN;
        this.playerReputation = Maps.newHashMap();
        this.villageAgressors = Lists.newArrayList();
        this.capabilities = ForgeEventFactory.gatherCapabilities(this);
    }
    
    public Village(final World world) {
        this.villageDoorInfoList = Lists.newArrayList();
        this.centerHelper = BlockPos.ORIGIN;
        this.center = BlockPos.ORIGIN;
        this.playerReputation = Maps.newHashMap();
        this.villageAgressors = Lists.newArrayList();
        this.world = world;
        this.capabilities = ForgeEventFactory.gatherCapabilities(this);
    }
    
    public void setWorld(final World world) {
        this.world = world;
    }
    
    public void tick(final int tickCounter) {
        this.tickCounter = tickCounter;
        this.removeDeadAndOutOfRangeDoors();
        this.removeDeadAndOldAgressors();
        if (tickCounter % 20 == 0) {
            this.updateNumVillagers();
        }
        if (tickCounter % 30 == 0) {
            this.updateNumIronGolems();
        }
        if (this.numIronGolems < this.numVillagers / 10 && this.villageDoorInfoList.size() > 20 && this.world.rand.nextInt(7000) == 0) {
            final Vec3d findRandomSpawnPos = this.findRandomSpawnPos(this.center, 2, 4, 2);
            if (findRandomSpawnPos != null) {
                final EntityIronGolem entityIronGolem = new EntityIronGolem(this.world);
                entityIronGolem.setPosition(findRandomSpawnPos.x, findRandomSpawnPos.y, findRandomSpawnPos.z);
                this.world.spawnEntity((Entity)entityIronGolem);
                ++this.numIronGolems;
            }
        }
    }
    
    private Vec3d findRandomSpawnPos(final BlockPos blockPos, final int n, final int n2, final int n3) {
        for (int i = 0; i < 10; ++i) {
            final BlockPos add = blockPos.add(this.world.rand.nextInt(16) - 8, this.world.rand.nextInt(6) - 3, this.world.rand.nextInt(16) - 8);
            if (this.isBlockPosWithinSqVillageRadius(add) && this.isAreaClearAround(new BlockPos(n, n2, n3), add)) {
                return new Vec3d((double)add.getX(), (double)add.getY(), (double)add.getZ());
            }
        }
        return null;
    }
    
    private boolean isAreaClearAround(final BlockPos blockPos, final BlockPos blockPos2) {
        if (!this.world.getBlockState(blockPos2.down()).isTopSolid()) {
            return false;
        }
        final int n = blockPos2.getX() - blockPos.getX() / 2;
        final int n2 = blockPos2.getZ() - blockPos.getZ() / 2;
        for (int i = n; i < n + blockPos.getX(); ++i) {
            for (int j = blockPos2.getY(); j < blockPos2.getY() + blockPos.getY(); ++j) {
                for (int k = n2; k < n2 + blockPos.getZ(); ++k) {
                    if (this.world.getBlockState(new BlockPos(i, j, k)).isNormalCube()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void updateNumIronGolems() {
        this.numIronGolems = this.world.getEntitiesWithinAABB((Class)EntityIronGolem.class, new AxisAlignedBB((double)(this.center.getX() - this.villageRadius), (double)(this.center.getY() - 4), (double)(this.center.getZ() - this.villageRadius), (double)(this.center.getX() + this.villageRadius), (double)(this.center.getY() + 4), (double)(this.center.getZ() + this.villageRadius))).size();
    }
    
    private void updateNumVillagers() {
        this.numVillagers = this.world.getEntitiesWithinAABB((Class)EntityVillager.class, new AxisAlignedBB((double)(this.center.getX() - this.villageRadius), (double)(this.center.getY() - 4), (double)(this.center.getZ() - this.villageRadius), (double)(this.center.getX() + this.villageRadius), (double)(this.center.getY() + 4), (double)(this.center.getZ() + this.villageRadius))).size();
        if (this.numVillagers == 0) {
            this.playerReputation.clear();
        }
    }
    
    public BlockPos getCenter() {
        return this.center;
    }
    
    public int getVillageRadius() {
        return this.villageRadius;
    }
    
    public int getNumVillageDoors() {
        return this.villageDoorInfoList.size();
    }
    
    public int getTicksSinceLastDoorAdding() {
        return this.tickCounter - this.lastAddDoorTimestamp;
    }
    
    public int getNumVillagers() {
        return this.numVillagers;
    }
    
    public boolean isBlockPosWithinSqVillageRadius(final BlockPos blockPos) {
        return this.center.distanceSq((Vec3i)blockPos) < this.villageRadius * this.villageRadius;
    }
    
    public List getVillageDoorInfoList() {
        return this.villageDoorInfoList;
    }
    
    public VillageDoorInfo getNearestDoor(final BlockPos blockPos) {
        VillageDoorInfo villageDoorInfo = null;
        int n = Integer.MAX_VALUE;
        for (final VillageDoorInfo villageDoorInfo2 : this.villageDoorInfoList) {
            final int getDistanceToDoorBlockSq = villageDoorInfo2.getDistanceToDoorBlockSq(blockPos);
            if (getDistanceToDoorBlockSq < n) {
                villageDoorInfo = villageDoorInfo2;
                n = getDistanceToDoorBlockSq;
            }
        }
        return villageDoorInfo;
    }
    
    public VillageDoorInfo getDoorInfo(final BlockPos blockPos) {
        VillageDoorInfo villageDoorInfo = null;
        int n = Integer.MAX_VALUE;
        for (final VillageDoorInfo villageDoorInfo2 : this.villageDoorInfoList) {
            final int getDistanceToDoorBlockSq = villageDoorInfo2.getDistanceToDoorBlockSq(blockPos);
            int getDoorOpeningRestrictionCounter;
            if (getDistanceToDoorBlockSq > 256) {
                getDoorOpeningRestrictionCounter = getDistanceToDoorBlockSq * 1000;
            }
            else {
                getDoorOpeningRestrictionCounter = villageDoorInfo2.getDoorOpeningRestrictionCounter();
            }
            if (getDoorOpeningRestrictionCounter < n) {
                final BlockPos getDoorBlockPos = villageDoorInfo2.getDoorBlockPos();
                final EnumFacing getInsideDirection = villageDoorInfo2.getInsideDirection();
                if (!this.world.getBlockState(getDoorBlockPos.offset(getInsideDirection, 1)).getBlock().isPassable((IBlockAccess)this.world, getDoorBlockPos.offset(getInsideDirection, 1)) || !this.world.getBlockState(getDoorBlockPos.offset(getInsideDirection, -1)).getBlock().isPassable((IBlockAccess)this.world, getDoorBlockPos.offset(getInsideDirection, -1)) || !this.world.getBlockState(getDoorBlockPos.up().offset(getInsideDirection, 1)).getBlock().isPassable((IBlockAccess)this.world, getDoorBlockPos.up().offset(getInsideDirection, 1)) || !this.world.getBlockState(getDoorBlockPos.up().offset(getInsideDirection, -1)).getBlock().isPassable((IBlockAccess)this.world, getDoorBlockPos.up().offset(getInsideDirection, -1))) {
                    continue;
                }
                villageDoorInfo = villageDoorInfo2;
                n = getDoorOpeningRestrictionCounter;
            }
        }
        return villageDoorInfo;
    }
    
    @Nullable
    public VillageDoorInfo getExistedDoor(final BlockPos blockPos) {
        if (this.center.distanceSq((Vec3i)blockPos) > this.villageRadius * this.villageRadius) {
            return null;
        }
        for (final VillageDoorInfo villageDoorInfo : this.villageDoorInfoList) {
            if (villageDoorInfo.getDoorBlockPos().getX() == blockPos.getX() && villageDoorInfo.getDoorBlockPos().getZ() == blockPos.getZ() && Math.abs(villageDoorInfo.getDoorBlockPos().getY() - blockPos.getY()) <= 1) {
                return villageDoorInfo;
            }
        }
        return null;
    }
    
    public void addVillageDoorInfo(final VillageDoorInfo villageDoorInfo) {
        this.villageDoorInfoList.add(villageDoorInfo);
        this.centerHelper = this.centerHelper.add((Vec3i)villageDoorInfo.getDoorBlockPos());
        this.updateVillageRadiusAndCenter();
        this.lastAddDoorTimestamp = villageDoorInfo.getLastActivityTimestamp();
    }
    
    public boolean isAnnihilated() {
        return this.villageDoorInfoList.isEmpty();
    }
    
    public void addOrRenewAgressor(final EntityLivingBase entityLivingBase) {
        for (final Village.a a : this.villageAgressors) {
            if (a.agressor == entityLivingBase) {
                a.agressionTime = this.tickCounter;
                return;
            }
        }
        this.villageAgressors.add(new Village.a(this, entityLivingBase, this.tickCounter));
    }
    
    @Nullable
    public EntityLivingBase findNearestVillageAggressor(final EntityLivingBase entityLivingBase) {
        double n = Double.MAX_VALUE;
        Village.a a = null;
        for (int i = 0; i < this.villageAgressors.size(); ++i) {
            final Village.a a2 = this.villageAgressors.get(i);
            final double getDistanceSq = a2.agressor.getDistanceSq((Entity)entityLivingBase);
            if (getDistanceSq <= n) {
                a = a2;
                n = getDistanceSq;
            }
        }
        return (a == null) ? null : a.agressor;
    }
    
    public EntityPlayer getNearestTargetPlayer(final EntityLivingBase entityLivingBase) {
        double n = Double.MAX_VALUE;
        EntityPlayer entityPlayer = null;
        for (final UUID uuid : this.playerReputation.keySet()) {
            if (this.isPlayerReputationTooLow(uuid)) {
                final EntityPlayer getPlayerEntityByUUID = this.world.getPlayerEntityByUUID(uuid);
                if (getPlayerEntityByUUID == null) {
                    continue;
                }
                final double getDistanceSq = getPlayerEntityByUUID.getDistanceSq((Entity)entityLivingBase);
                if (getDistanceSq > n) {
                    continue;
                }
                entityPlayer = getPlayerEntityByUUID;
                n = getDistanceSq;
            }
        }
        return entityPlayer;
    }
    
    private void removeDeadAndOldAgressors() {
        final Iterator<Village.a> iterator = (Iterator<Village.a>)this.villageAgressors.iterator();
        while (iterator.hasNext()) {
            final Village.a a = iterator.next();
            if (!a.agressor.isEntityAlive() || Math.abs(this.tickCounter - a.agressionTime) > 300) {
                iterator.remove();
            }
        }
    }
    
    private void removeDeadAndOutOfRangeDoors() {
        boolean b = false;
        final boolean b2 = this.world.rand.nextInt(50) == 0;
        final Iterator<VillageDoorInfo> iterator = (Iterator<VillageDoorInfo>)this.villageDoorInfoList.iterator();
        while (iterator.hasNext()) {
            final VillageDoorInfo villageDoorInfo = iterator.next();
            if (b2) {
                villageDoorInfo.resetDoorOpeningRestrictionCounter();
            }
            if (this.world.isBlockLoaded(villageDoorInfo.getDoorBlockPos()) && (!this.isWoodDoor(villageDoorInfo.getDoorBlockPos()) || Math.abs(this.tickCounter - villageDoorInfo.getLastActivityTimestamp()) > 1200)) {
                this.centerHelper = this.centerHelper.subtract((Vec3i)villageDoorInfo.getDoorBlockPos());
                b = true;
                villageDoorInfo.setIsDetachedFromVillageFlag(true);
                iterator.remove();
            }
        }
        if (b) {
            this.updateVillageRadiusAndCenter();
        }
    }
    
    private boolean isWoodDoor(final BlockPos blockPos) {
        final IBlockState getBlockState = this.world.getBlockState(blockPos);
        return getBlockState.getBlock() instanceof BlockDoor && getBlockState.getMaterial() == Material.WOOD;
    }
    
    private void updateVillageRadiusAndCenter() {
        final int size = this.villageDoorInfoList.size();
        if (size == 0) {
            this.center = BlockPos.ORIGIN;
            this.villageRadius = 0;
        }
        else {
            this.center = new BlockPos(this.centerHelper.getX() / size, this.centerHelper.getY() / size, this.centerHelper.getZ() / size);
            int max = 0;
            final Iterator<VillageDoorInfo> iterator = this.villageDoorInfoList.iterator();
            while (iterator.hasNext()) {
                max = Math.max(iterator.next().getDistanceToDoorBlockSq(this.center), max);
            }
            this.villageRadius = Math.max(32, (int)Math.sqrt(max) + 1);
        }
    }
    
    @Deprecated
    public int getPlayerReputation(final String s) {
        return this.getPlayerReputation(this.findUUID(s));
    }
    
    public int getPlayerReputation(final UUID uuid) {
        final Integer n = this.playerReputation.get(uuid);
        return (n == null) ? 0 : n;
    }
    
    private UUID findUUID(final String s) {
        if (this.world == null || this.world.getMinecraftServer() == null) {
            return EntityPlayer.getOfflineUUID(s);
        }
        final GameProfile getGameProfileForUsername = this.world.getMinecraftServer().getPlayerProfileCache().getGameProfileForUsername(s);
        return (getGameProfileForUsername == null) ? EntityPlayer.getOfflineUUID(s) : getGameProfileForUsername.getId();
    }
    
    @Deprecated
    public int modifyPlayerReputation(final String s, final int n) {
        return this.modifyPlayerReputation(this.findUUID(s), n);
    }
    
    public int modifyPlayerReputation(final UUID uuid, final int n) {
        final int clamp = MathHelper.clamp(this.getPlayerReputation(uuid) + n, -30, 10);
        this.playerReputation.put(uuid, clamp);
        return clamp;
    }
    
    @Deprecated
    public boolean isPlayerReputationTooLow(final String s) {
        return this.isPlayerReputationTooLow(this.findUUID(s));
    }
    
    public boolean isPlayerReputationTooLow(final UUID uuid) {
        return this.getPlayerReputation(uuid) <= -15;
    }
    
    public void readVillageDataFromNBT(final NBTTagCompound nbtTagCompound) {
        this.numVillagers = nbtTagCompound.getInteger("PopSize");
        this.villageRadius = nbtTagCompound.getInteger("Radius");
        this.numIronGolems = nbtTagCompound.getInteger("Golems");
        this.lastAddDoorTimestamp = nbtTagCompound.getInteger("Stable");
        this.tickCounter = nbtTagCompound.getInteger("Tick");
        this.noBreedTicks = nbtTagCompound.getInteger("MTick");
        this.center = new BlockPos(nbtTagCompound.getInteger("CX"), nbtTagCompound.getInteger("CY"), nbtTagCompound.getInteger("CZ"));
        this.centerHelper = new BlockPos(nbtTagCompound.getInteger("ACX"), nbtTagCompound.getInteger("ACY"), nbtTagCompound.getInteger("ACZ"));
        final NBTTagList getTagList = nbtTagCompound.getTagList("Doors", 10);
        for (int i = 0; i < getTagList.tagCount(); ++i) {
            final NBTTagCompound getCompoundTagAt = getTagList.getCompoundTagAt(i);
            this.villageDoorInfoList.add(new VillageDoorInfo(new BlockPos(getCompoundTagAt.getInteger("X"), getCompoundTagAt.getInteger("Y"), getCompoundTagAt.getInteger("Z")), getCompoundTagAt.getInteger("IDX"), getCompoundTagAt.getInteger("IDZ"), getCompoundTagAt.getInteger("TS")));
        }
        final NBTTagList getTagList2 = nbtTagCompound.getTagList("Players", 10);
        for (int j = 0; j < getTagList2.tagCount(); ++j) {
            final NBTTagCompound getCompoundTagAt2 = getTagList2.getCompoundTagAt(j);
            if (getCompoundTagAt2.hasKey("UUID")) {
                this.playerReputation.put(UUID.fromString(getCompoundTagAt2.getString("UUID")), getCompoundTagAt2.getInteger("S"));
            }
            else {
                this.playerReputation.put(this.findUUID(getCompoundTagAt2.getString("Name")), getCompoundTagAt2.getInteger("S"));
            }
        }
        if (this.capabilities != null && nbtTagCompound.hasKey("ForgeCaps")) {
            this.capabilities.deserializeNBT(nbtTagCompound.getCompoundTag("ForgeCaps"));
        }
    }
    
    public void writeVillageDataToNBT(final NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("PopSize", this.numVillagers);
        nbtTagCompound.setInteger("Radius", this.villageRadius);
        nbtTagCompound.setInteger("Golems", this.numIronGolems);
        nbtTagCompound.setInteger("Stable", this.lastAddDoorTimestamp);
        nbtTagCompound.setInteger("Tick", this.tickCounter);
        nbtTagCompound.setInteger("MTick", this.noBreedTicks);
        nbtTagCompound.setInteger("CX", this.center.getX());
        nbtTagCompound.setInteger("CY", this.center.getY());
        nbtTagCompound.setInteger("CZ", this.center.getZ());
        nbtTagCompound.setInteger("ACX", this.centerHelper.getX());
        nbtTagCompound.setInteger("ACY", this.centerHelper.getY());
        nbtTagCompound.setInteger("ACZ", this.centerHelper.getZ());
        final NBTTagList list = new NBTTagList();
        for (final VillageDoorInfo villageDoorInfo : this.villageDoorInfoList) {
            final NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
            nbtTagCompound2.setInteger("X", villageDoorInfo.getDoorBlockPos().getX());
            nbtTagCompound2.setInteger("Y", villageDoorInfo.getDoorBlockPos().getY());
            nbtTagCompound2.setInteger("Z", villageDoorInfo.getDoorBlockPos().getZ());
            nbtTagCompound2.setInteger("IDX", villageDoorInfo.getInsideOffsetX());
            nbtTagCompound2.setInteger("IDZ", villageDoorInfo.getInsideOffsetZ());
            nbtTagCompound2.setInteger("TS", villageDoorInfo.getLastActivityTimestamp());
            list.appendTag((NBTBase)nbtTagCompound2);
        }
        nbtTagCompound.setTag("Doors", (NBTBase)list);
        final NBTTagList list2 = new NBTTagList();
        for (final UUID uuid : this.playerReputation.keySet()) {
            final NBTTagCompound nbtTagCompound3 = new NBTTagCompound();
            try {
                nbtTagCompound3.setString("UUID", uuid.toString());
                nbtTagCompound3.setInteger("S", (int)this.playerReputation.get(uuid));
                list2.appendTag((NBTBase)nbtTagCompound3);
            }
            catch (RuntimeException ex) {}
        }
        nbtTagCompound.setTag("Players", (NBTBase)list2);
        if (this.capabilities != null) {
            nbtTagCompound.setTag("ForgeCaps", (NBTBase)this.capabilities.serializeNBT());
        }
    }
    
    public void endMatingSeason() {
        this.noBreedTicks = this.tickCounter;
    }
    
    public boolean isMatingSeason() {
        return this.noBreedTicks == 0 || this.tickCounter - this.noBreedTicks >= 3600;
    }
    
    public void setDefaultPlayerReputation(final int n) {
        final Iterator<UUID> iterator = this.playerReputation.keySet().iterator();
        while (iterator.hasNext()) {
            this.modifyPlayerReputation(iterator.next(), n);
        }
    }
    
    public boolean hasCapability(final Capability capability, @Nullable final EnumFacing enumFacing) {
        return this.capabilities != null && this.capabilities.hasCapability(capability, enumFacing);
    }
    
    @Nullable
    public Object getCapability(final Capability capability, @Nullable final EnumFacing enumFacing) {
        return (this.capabilities == null) ? null : this.capabilities.getCapability(capability, enumFacing);
    }
    
    public void deserializeNBT(final NBTTagCompound nbtTagCompound) {
        this.readVillageDataFromNBT(nbtTagCompound);
    }
    
    public NBTTagCompound serializeNBT() {
        final NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeVillageDataToNBT(nbtTagCompound);
        return nbtTagCompound;
    }
    
    public void deserializeNBT(final NBTBase nbtBase) {
        this.deserializeNBT((NBTTagCompound)nbtBase);
    }
    
    public NBTBase serializeNBT() {
        return (NBTBase)this.serializeNBT();
    }
}
