package gov.nist.jrolie.model;

import java.net.URI;
import java.util.ArrayList;

public class JData implements JResource {

	String path = null;
	String id = null;
	String data = null;
	
	public String getData()
	{
		return data;
		
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	@Override
	public URI getBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBase(URI base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLang() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLang(String lang) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<JAttribute> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChanged(boolean changed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}

}
