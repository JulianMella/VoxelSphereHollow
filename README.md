A Vibe-Coded minecraft mod to get normalized coordinates of all voxels in a hollow voxel sphere.

The way this works is that you first generate a hollow sphere with WorldEdit in Minecraft and then at its origo you place "Dev Block" from this mod. Then you make sure that it has the same radius as the sphere by right clicking with a diamond sword to increment its scanning radius area by 1 or diamond hoe to decrement it. Once it matches to the radius of the hollow sphere, right click it with a Diamond Pickaxe to write all the normalized coordinates to a .txt file which __you__ specify in ParserBlock.java. 

This was utilized in [PyTensor](https://github.com/JulianMella/PyTensor)
