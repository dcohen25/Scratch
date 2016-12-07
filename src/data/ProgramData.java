package data;

import java.util.List;

public class ProgramData {

	private String _device;
	private List<String> _commands;
	
	public ProgramData(String device, List<String> commands){
		_device = device;
		_commands = commands;
	}
	
	public String getDevice(){
		return _device;
	}
	
	public List<String> getCommands(){
		return _commands;
	}
	
	public void setDevice(String device){
		_device = device;
	}
	
	public void setCommands(List<String> commands){
		_commands = commands;
	}
}
