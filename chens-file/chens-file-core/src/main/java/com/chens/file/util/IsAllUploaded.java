package com.chens.file.util;


import com.chens.file.service.IFileInfoService;
import com.chens.file.entity.SysFile;
import com.chens.file.vo.FileInfo;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.chens.file.util.FileUtil.mergeFile;

public class IsAllUploaded {

    private final static List<FileInfo> uploadInfoList = new ArrayList<>();

    /**
     * @param md5
     * @param chunks
     * @return
     */
    public static boolean isAllUploaded(@NotNull final String md5,
                                        @NotNull final String chunks) {
        int size = uploadInfoList.stream()
                .filter(item -> item.getMd5().equals(md5))
                .distinct()
                .collect(Collectors.toList())
                .size();
        boolean bool = (size == Integer.parseInt(chunks));
        if (bool) {
            synchronized (uploadInfoList) {
                uploadInfoList.removeIf(item -> Objects.equals(item.getMd5(), md5));
            }
        }
        return bool;
    }

    /**
     * @param md5         MD5
     * @param guid        随机生成的文件名
     * @param chunk       文件分块序号
     * @param chunks      文件分块数
     * @param fileName    文件名
     * @param ext         文件后缀名
     * @param fileService fileService
     */
    /*
    public static void uploaded(@NotNull final String md5,
                                @NotNull final String guid,
                                @NotNull final String chunk,
                                @NotNull final String chunks,
                                @NotNull final String uploadFolderPath,
                                @NotNull final String fileName,
                                @NotNull final String ext,
                                @NotNull final IFileInfoService fileService)
            throws Exception {
        synchronized (uploadInfoList) {
            uploadInfoList.add(new FileInfo(md5,guid,chunks, chunk, uploadFolderPath, fileName, ext));
        }
        boolean allUploaded = isAllUploaded(md5, chunks);
        int chunksNumber = Integer.parseInt(chunks);

        if (allUploaded) {
            mergeFile(chunksNumber, ext, guid, uploadFolderPath);
            fileService.insert(new SysFile(guid + ext, 1L,md5));
        }
    }
    */
}



