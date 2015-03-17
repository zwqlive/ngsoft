package org.ngsoft.game.test.message;

import io.netty.buffer.ByteBuf;

import org.ngsoft.core.message.ITransportable;
import org.ngsoft.core.message.Message;
import org.ngsoft.game.test.transobj.SomeInfo;

public class SCTestMessage extends Message {
	private int age;
	private String name;
	private byte sex;
	private SomeInfo someInfo;
	
	@Override
	public void write(ByteBuf byteBuf) {
		writeInt(byteBuf,age);
		writeString(byteBuf,name);
		writeByte(byteBuf,sex);
		writeObject(byteBuf,someInfo);		
	}

	@Override
	public void read(ByteBuf byteBuf) {
		age = readInt(byteBuf);
		name=readString(byteBuf);
		sex = readByte(byteBuf);
		ITransportable trans_SomeInfo = readObject(byteBuf,SomeInfo.class);
		if(trans_SomeInfo instanceof SomeInfo){
			someInfo =	(SomeInfo)trans_SomeInfo;
		}
	}

	@Override
	public int getId() {
		return 101201;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public SomeInfo getSomeInfo() {
		return someInfo;
	}

	public void setSomeInfo(SomeInfo someInfo) {
		this.someInfo = someInfo;
	}
	

}
