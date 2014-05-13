package org.ngsoft.server.message;

import io.netty.buffer.ByteBuf;

import org.ngsoft.core.message.Message;

public class SCTestMessage extends Message {
	private int age;
	private String name;
	private byte sex;
	
	@Override
	public void write(ByteBuf byteBuf) {
		writeInt(byteBuf,age);
		writeString(byteBuf,name);
		writeByte(byteBuf,sex);
	}

	@Override
	public void read(ByteBuf byteBuf) {
		age = readInt(byteBuf);
		name=readString(byteBuf);
		sex = readByte(byteBuf);
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
	
	

}
