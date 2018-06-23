package org.nwnu.base;

import java.io.File;

import org.junit.Test;

public class filePathTest {
	@Test
	public void showPath(){
		File f=new File("/");
		System.out.println("*****************"+f.getPath());
	}
}
