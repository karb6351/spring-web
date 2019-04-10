package project.views;

import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DownloadView implements View {

    private final String filename;
    private final String contentType;
    private final byte[] contents;

    public DownloadView(String filename, String contentType, byte[] contents) {
        this.filename = filename;
        this.contentType = contentType;
        this.contents = contents;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + this.filename);
        httpServletResponse.setContentType("application/octet-stream");

        ServletOutputStream stream = httpServletResponse.getOutputStream();
        stream.write(this.contents);
    }
}
