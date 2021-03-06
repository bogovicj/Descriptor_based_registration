package plugin;

import ij.gui.Roi;

import java.util.ArrayList;

import mpicbg.models.AbstractModel;
import mpicbg.models.InvertibleBoundable;
import mpicbg.models.PointMatch;
import mpicbg.models.TranslationModel2D;
import mpicbg.models.TranslationModel3D;

public class DescriptorParameters 
{	
	/**
	 * How many iterations for a RANSAC
	 */
	public static int ransacIterations = 1000;
	
	/**
	 * minimal number of inliers to number of
	 * candidates in RANSAC
	 */
	public static float minInlierRatio = 0.05f;

	/**
	 * if there is a ROI designed, how many iterations
	 */
	public static int maxIterations = 5;

	/**
	 * Max trust: reject candidates with a cost 
	 * larger than maxTrust * median cost 
	 */
	public static float maxTrust = 4f;
	
	/**
	 * How many times more inliers are required
	 * than the minimum number of correspondences
	 * required for the model.
	 * 
	 * E.g. AffineModel3d needs at least 4 corresponences,
	 * so we reject if the number of inliers is smaller
	 * than minInlierFactor*4 
	 */
	public static float minInlierFactor = 2f;
	
	/**
	 * if true we use filterRANSAC, otherwise only RANSAC
	 */
	public static boolean filterRANSAC = true;
	
	/**
	 * How similar two descriptors at least have to be
	 */
	public static double minSimilarity = 100;
	
	/**
	 * Writes out all corresponding points of all pairs if this is set to a directory
	 */
	public static String correspondenceDirectory = null;

	/**
	 * Just keep the brightest N points of all detections
	 */
	public static int brightestNPoints = 0;

	/**
	 * 0 == compute per image (per timepoint/channel individually)
	 * 1 == compute global min/max
	 * 2 == define min/max
	 */
	public static int minMaxType = 0;
	public static double min = 0;
	public static double max = 0;

	// for debug
	public static boolean printAllSimilarities = false;

	public int dimensionality;
	public double sigma1, sigma2, threshold;
	public int localization = 1; //localizationChoice = { "None", "3-dimensional quadratic fit", "Gaussian mask localization fit" };
	public boolean lookForMaxima, lookForMinima;
	public AbstractModel<?> model;
	public boolean similarOrientation;
	public int numNeighbors;
	public int redundancy;
	public double significance;
	public double ransacThreshold;
	public int channel1, channel2;
	
	public boolean regularize = false;
	public boolean fixFirstTile = true;
	public double lambda = 0.1;
	
	// for stack-registration
	public int globalOpt; // 0=all-to-all; 1=all-to-all-withrange; 2=all-to-1; 3=Consecutive
	public int range;	
	public String directory;
	
	public boolean reApply = false;
	public Roi roi1, roi2;
	
	public boolean setPointsRois = true;
	
	// Display anything?
	public boolean silent = false;

	// 0 == fuse in memory, 1 == write to disk, 2 == nothing
	public int fuse = 0;
	
	protected AbstractModel< ? > initialModel = null; 
	public AbstractModel<?> getInitialModel()
	{
		if ( initialModel != null )
			return initialModel;
		else if ( this.dimensionality == 2 )
			return new TranslationModel2D();
		else
			return new TranslationModel3D();

	}
	
	// for java-based calling
	public boolean storePoints = false;
	public boolean storeModels = false;
	
	public ArrayList<PointMatch> inliers = null;
	public InvertibleBoundable model1 = null;
	public InvertibleBoundable model2 = null;

	// gaussian parameters
	public double[] sigma;
	public int[] region;
	public int iterations;
}
