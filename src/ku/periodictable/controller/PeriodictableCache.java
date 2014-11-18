package ku.periodictable.controller;
import java.util.HashMap;
import net.webservicex.entity.Element;

/**
 * Cache class for Periodictable Webservice.
 * Use HashMap to be a cache.
 * have get, add and contains method.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class PeriodictableCache {

	/**
	 * hashmap act as cache.
	 */
	private HashMap<String, Element> cache;
	
	/**
	 * default constructor.
	 */
	public PeriodictableCache() {
		cache = new HashMap<String, Element>();
	}
	
	/**
	 * get element info from specific keyword.
	 * @param keyword to use as key in hashmap.
	 * @return element info.
	 */
	public Element getInfo(String keyword) {
		return cache.get(keyword);
	}
	
	/**
	 * add new keyword and element info to cache.
	 * @param keyword to add
	 * @param info to add
	 */
	public void addToCache(String keyword, Element info) {
		cache.put(keyword, info);
	}
	
	/**
	 * check exist key in cache.
	 * @param key to check.
	 * @return true if found false otherwise.
	 */
	public boolean containsKey(String key) {
		return cache.containsKey(key);
	}
}
