package com.yingbao.career.video;

import com.easefun.polyvsdk.PolyvDownloader;

public class PolyvDownloadInfo {
    // vid
    private String vid;
    // 时长
    private String duration;
    // 文件大小
    private long filesize;
    // 码率
    private int bitrate;
    // 标题
    private String title;
    // 已下载的文件大小(mp4)/已下载的文件个数(ts)
    private long percent;
    // 总文件大小(mp4)/总个数(ts)
    private long total;
    // 下载的文件类型
    private int fileType;

    private int courseId;

    private String videoCover;

    public PolyvDownloadInfo(String vid, String duration, long filesize, int bitrate, String title,int courseId,String videoCover) {
        this(vid,duration, filesize, bitrate, title,courseId,videoCover,PolyvDownloader.FILE_VIDEO);
    }

    public PolyvDownloadInfo(String  vid, String duration, long filesize, int bitrate, String title,int courseId,String videoCover, int fileType) {
        this.courseId = courseId;
        this.vid = vid;
        this.duration = duration;
        this.filesize = filesize;
        this.bitrate = bitrate;
        this.title = title;
        this.videoCover = videoCover;
        this.fileType = fileType;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPercent() {
        return percent;
    }

    public void setPercent(long percent) {
        this.percent = percent;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    @Override
    public String toString() {
        return "PolyvDownloadInfo{" +
                "vid='" + vid + '\'' +
                ", duration='" + duration + '\'' +
                ", filesize=" + filesize +
                ", bitrate=" + bitrate +
                ", title='" + title + '\'' +
                ", percent=" + percent +
                ", total=" + total +
                ", fileType=" + fileType +
                ", courseId=" + courseId +
                ", videoCover=" + videoCover +
                '}';
    }
}
