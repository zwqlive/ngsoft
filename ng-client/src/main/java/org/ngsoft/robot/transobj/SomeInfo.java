package org.ngsoft.robot.transobj;

import io.netty.buffer.ByteBuf;

import org.ngsoft.core.message.TransportObject;

public class SomeInfo extends TransportObject{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void write(ByteBuf byteBuf) {
		writeInt(byteBuf,id);
		writeString(byteBuf,name);
		
	}

	@Override
	public void read(ByteBuf byteBuf) {
		id = readInt(byteBuf);
		name=readString(byteBuf);
	}
	
	@Override
	public String toString(){
		return "[id:"+id+",name:"+name+"]";
	}

}
