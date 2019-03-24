package net.kurien.blog.common.template.metadata;

import java.util.ArrayList;
import java.util.List;

public class TemplateMetadata {
	public List<String> metadataTags = new ArrayList<String>();
	
	public boolean add(String metadataTag) {
		return metadataTags.add(metadataTag);
	}

	public int size() {
		return metadataTags.size();
	}

	public String remove(int index) {
		return metadataTags.remove(index);
	}
	
	public String toString() {
		String str = "";
		
		for(String metadataTag : metadataTags) {
			str += metadataTag + "\n"; 
		}
		
		return str;
	}
}
