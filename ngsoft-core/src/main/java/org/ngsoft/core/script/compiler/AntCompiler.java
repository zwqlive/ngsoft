package org.ngsoft.core.script.compiler;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * 使用ant api编译源文件
 * 
 * @author will
 *
 */
public class AntCompiler implements ICompiler{
	private String buildFilePath="script_build.xml";
	
	@Override
	public void compile(){
		Project project = new Project();
		File buildFile = new File(buildFilePath);
		ProjectHelper projHelper = ProjectHelper.getProjectHelper();
		project.fireBuildStarted();
		project.init();
		projHelper.parse(project, buildFile);
		project.executeTarget(project.getDefaultTarget());
		project.fireBuildFinished(null);
	}
}
