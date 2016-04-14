package cm.model;

/**
 * 类名：fm.model.FileUploadStatus
 * 创建者： CM .
 * 创建时间：2016/3/17
 */
public class FileUploadStatus {
    private Long readLength;
    private Long fileLength;
    private Integer currentItemIndex;

    public Long getReadLength() {
        return readLength;
    }

    public void setReadLength(Long readLength) {
        this.readLength = readLength;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public Integer getCurrentItemIndex() {
        return currentItemIndex;
    }

    public void setCurrentItemIndex(Integer currentItemIndex) {
        this.currentItemIndex = currentItemIndex;
    }
}
