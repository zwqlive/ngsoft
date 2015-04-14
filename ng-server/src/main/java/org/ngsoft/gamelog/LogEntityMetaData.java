package org.ngsoft.gamelog;

import org.ngsoft.db.util.ColumnInfo;

public class LogEntityMetaData {
		private String fieldName;
		private String fieldType;
		private String mate;
		public LogEntityMetaData(String fieldName,String fieldType){
			this.fieldName=fieldName;
			this.fieldType=fieldType;
			this.mate="`"+fieldName+"`\t"+fieldType;
		}
		public String getFieldName() {
			return fieldName;
		}
		public String getFieldType() {
			return fieldType;
		}
		public String toString(){
			return mate;
		}
		
		public ColumnInfo toColumnInfo(){
			ColumnInfo info=new ColumnInfo();
			info.setName(getFieldName());
			if(fieldType.contains("(")){
				String replace = fieldType.replace(")", "");
				String[] split = replace.split("\\(");
				info.setType(split[0]);
				info.setSize(Integer.valueOf(split[1]));
				info.setNullable(true);
			}else{
				info.setType(getFieldType());
				info.setSize(0);
				info.setNullable(true);
			}
			return info;
		}
	}