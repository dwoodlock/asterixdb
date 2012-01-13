package edu.uci.ics.hyracks.storage.am.lsmtree.rtree.impls;

import edu.uci.ics.hyracks.storage.common.buffercache.HeapBufferAllocator;
import edu.uci.ics.hyracks.storage.common.buffercache.IBufferCache;
import edu.uci.ics.hyracks.storage.common.buffercache.ICacheMemoryAllocator;

public class LSMRTreeInMemoryBufferCacheFactory {
	
	private IBufferCache bufferCache;
    private final int pageSize;
    private final int numPages;
	
    public LSMRTreeInMemoryBufferCacheFactory(int pageSize, int numPages) {
    	this.pageSize = pageSize;
    	this.numPages = numPages;
        bufferCache = null;
    }
    
    public synchronized IBufferCache createInMemoryBufferCache() {
        if (bufferCache == null) {
            ICacheMemoryAllocator allocator = new HeapBufferAllocator();
            bufferCache = new LSMRTreeInMemoryBufferCache(allocator, pageSize, numPages);
        }
        return bufferCache;
    }
}
