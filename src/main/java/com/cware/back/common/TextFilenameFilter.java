package com.cware.back.common;

import java.io.File;
import java.io.FilenameFilter;
/**
 * 폴더에서 텍스트파일만 가지고 온다.
 * @version 1.0, 2009/01/21
 * @author 정승연
 *
 */
public class TextFilenameFilter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".txt");
    }
}
