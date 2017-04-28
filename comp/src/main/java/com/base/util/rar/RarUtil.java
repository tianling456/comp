package com.base.util.rar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.base.common.exception.app.AppException;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.RarException.RarExceptionType;
import com.github.junrar.rarfile.FileHeader;

/**
 * 项目名： 创建时间：2017-2-25 创建人：Aobo 类名：RarUtil 所属包名：com.rar 项目名称：test 类功能描述：
 */
public class RarUtil {
	/**
	 * 根据原始rar路径，解压到指定文件夹下.
	 * 
	 * @param srcRarPath
	 *            原始rar路径
	 * @param dstDirectoryPath
	 *            解压到的文件夹
	 */
	@SuppressWarnings("unused")
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
		File srcRarFile = new File(srcRarPath);
		if ("".equals(srcRarPath)||srcRarPath==null) {
			System.out.println("文件为空！");
			throw new AppException(srcRarPath + " 文件为空!");
		}
		if(!srcRarFile.exists()){
			System.out.println("rar文件不存在!");
			throw new AppException(srcRarPath + " rar文件不存在!");
		}
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("非rar文件！");
			throw new AppException(srcRarPath + " 非rar文件!");
		}
		File dstDiretory = new File(dstDirectoryPath);
		if(dstDiretory.exists()){
			System.out.println("文件已存在！");
			throw new AppException(dstDiretory + " 文件已存在!");
		}
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
			dstDiretory.mkdirs();
		}
		Archive archive = null;
		try {
			archive = new Archive(srcRarFile);
			if (archive == null) {
				throw new FileNotFoundException(srcRarPath + " NOT FOUND!");
			}
			if (archive.isEncrypted()) {
				throw new AppException(srcRarPath + " IS ENCRYPTED!");
			}
			if (archive != null) {
				// a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = archive.nextFileHeader();
				while (fh != null) {
					// 防止文件名中文乱码问题的处理
					String fileName = fh.getFileNameW().isEmpty() ? fh
							.getFileNameString() : fh.getFileNameW();
					if (fh.isDirectory()) { // 文件夹
						File fol = new File(dstDirectoryPath + File.separator
								+ fileName);
						fol.mkdirs();
					} else { // 文件
						File out = new File(dstDirectoryPath + File.separator
								+ fileName.trim());
						try {
							if (!out.exists()) {
								if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
									out.getParentFile().mkdirs();
								}
								out.createNewFile();
							}
							FileOutputStream os = new FileOutputStream(out);
							archive.extractFile(fh, os);
							os.close();
						} catch (RarException e) {
							if (e.getType().equals(
									RarExceptionType.notImplementedYet)) {
							}
						}
					}
					fh = archive.nextFileHeader();
				}
				archive.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String rarFileName = "C:\\Users\\Aobo\\Desktop\\123.rar";
		String outFilePath = "C:\\Users\\Aobo\\Desktop\\1234";
		try {
			RarUtil.unRarFile(rarFileName, outFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
