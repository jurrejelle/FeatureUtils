package kaptainwutax.featureutils.structure;

import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;

public abstract class TriangularStructure<T extends TriangularStructure<T>> extends RegionStructure<RegionStructure.Config, RegionStructure.Data<T>> {

	private final int peak;

	public TriangularStructure(RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.peak = this.getSpacing() - this.getSeparation();
	}

	public static String name() {
		return "triangular_structure";
	}

	public int getPeak() {
		return this.peak;
	}

	@Override
	public boolean canStart(Data<T> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2 == data.offsetX
				&& (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2 == data.offsetZ;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());

		return new CPos(
				regionX * this.getSpacing() + (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2,
				regionZ * this.getSpacing() + (rand.nextInt(this.peak) + rand.nextInt(this.peak)) / 2
		);
	}

}
