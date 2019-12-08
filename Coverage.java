import java.util.ArrayList;
import java.util.List;

public class Coverage {

	int start;
	int end;
	
	public int getLength()
	{
		return end - start;
	}
	
	// merges two coverages into a new coverage
	public Coverage merge(Coverage c)
	{
		int minStart = Math.min(this.start, c.start);
		int maxEnd = Math.max(this.end, c.end);
		return new Coverage(minStart, maxEnd);
	}
	
	// returns true if this coverage is overlapping or touching arg coverage
	public boolean areConnected(Coverage c)
	{
		// if starts before and ends after
		if( c.start <= this.start && c.end >= this.end ) return true; 
		// if ends between or connected (1 before)
		if ( c.end >= this.start -1 && c.end <= this.end ) return true;
		// if starts between
		if( c.start >= this.start && c.start <= this.end +1 ) return true;
		
		// else they are not connected
		return false;
	}
	
	public Coverage(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	// returns the longest coverage term in given list of coverage terms
	// if there are multiple terms with the same length that are the longest
	// they will all be returned
	public static List<Coverage> getLongestContinuousCoverage(List<Coverage> coverages)
	{
		if (coverages == null || coverages.size() < 1) 
			{ throw new IllegalArgumentException("No Coverage passed in as arg!"); }
		
		int longestLength = 0;
		List<Coverage> longestCoverages = new ArrayList<Coverage>();
		
		List<Coverage> mergedCoverages = new ArrayList<Coverage>();
		
		for(Coverage c : coverages)
		{
			Coverage merged = merge(mergedCoverages, c);
			
			if(merged.getLength() >= longestLength)
			{
				if(merged.getLength() > longestLength)
				{
					longestCoverages.clear();
					longestLength = merged.getLength();
				}
				longestCoverages.add(merged);
			}			
		}
		
		return longestCoverages;
	}
	
	// returns the merged coverage
	public static Coverage merge(List<Coverage> coverages, Coverage coverage)
	{
		Coverage merged = coverage;
		
		for(Coverage c: coverages)
		{
			if(c.areConnected(coverage))
			{
				coverages.remove(c);
				merged = c.merge(coverage);
				
				// recursive call to merge the 'merged' coverage with any other coverages
				merged = merge(coverages, merged);
				break;
			}
		}
		
		if(merged == coverage) coverages.add(coverage);
		
		return merged;
	}
}
