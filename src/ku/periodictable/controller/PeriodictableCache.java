package ku.periodictable.controller;
import java.util.HashMap;
import net.webservicex.entity.Element;


public class PeriodictableCache {

	private HashMap<String, Element> cache;
	
	public PeriodictableCache() {
		cache = new HashMap<String, Element>();
	}
	
	public Element getInfo(String keyword) {
		return cache.get(keyword);
	}
	
	public void addToCache(String keyword, Element info) {
		cache.put(keyword, info);
	}
	
	public boolean containsKey(String key) {
		return cache.containsKey(key);
	}
}
