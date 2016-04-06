package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.MimeTypeMap;

import com.onfengma.androidprac.Config.Constants;

import org.apache.http.util.ByteArrayBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> params = new HashMap<String, Object>();
                try {
                    params.put("name", "min");
                    params.put("age", "123123");
                    params.put("weight", new File("/storage/sdcard0/360powercontrol_3.2.2.160301.apk"));
                    sendMultiPost("http://10.32.24.114:5389/file", params);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendPost(String path) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String boundary = "----" + Long.toHexString(System.currentTimeMillis());
        builder.post(RequestBody.create(MediaType.parse("multipart/form-data; boundary=" + boundary), "adfasdfsdf"));
        builder.url(path);
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        System.out.println("------------" + response.body().toString());
    }

    private void sendMultiPost(String path, Map<String, Object> httpParams) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String boundary = "----" + Long.toHexString(System.currentTimeMillis());
        ByteArrayBuffer payload = new ByteArrayBuffer(4 * 1024);
        for (Map.Entry<String, Object> entry : httpParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (value instanceof List) {
                List<Object> list = (List<Object>) value;
                for (Object item : list) {
                    appendPayload(key, item, payload, boundary);
                }
            } else {
                appendPayload(key, value, payload, boundary);
            }
        }
        appendPayload(payload, "--");
        appendPayload(payload, boundary);
        appendPayload(payload, "--");
        appendPayload(payload, Constants.CRLF);

        Request.Builder builder = new Request.Builder();
        builder.url(path);
        builder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
        builder.addHeader("Content-Length", String.valueOf(payload.length()));
        builder.post(RequestBody.create(MediaType.parse("multipart/form-data; boundary=" + boundary), payload.toByteArray()));
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        System.out.println("------------" + response.body().toString());
    }


    private void appendPayload(String key, Object value, ByteArrayBuffer payload, String boundary) throws IOException {
        appendPayload(payload, "--");
        appendPayload(payload, boundary);
        appendPayload(payload, Constants.CRLF);
        if (value instanceof File) {
            InputStream input = null;
            try {
                File file = (File) value;
                String fileName = file.getName();
                appendPayload(payload, "Content-Disposition: form-data; name=\"");
                appendPayload(payload, key);
                appendPayload(payload, "\"; filename=\"");
                appendPayload(payload, fileName);
                appendPayload(payload, "\"");
                appendPayload(payload, Constants.CRLF);
                String contentType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(substringAfterLast(fileName, ".").toLowerCase());
                if (contentType == null) {
                    throw new IOException("Can not found MIME type for file: " + fileName);
                }
                appendPayload(payload, "Content-Type: ");
                appendPayload(payload, contentType);
                appendPayload(payload, Constants.CRLF);
                appendPayload(payload, Constants.CRLF);
                input = new FileInputStream(file);
                byte[] data = toByteArray(input);
                payload.append(data, 0, data.length);
                appendPayload(payload, Constants.CRLF);
            } finally {
                input.close();
            }
        } else {
            appendPayload(payload, "Content-Disposition: form-data; name=\"");
            appendPayload(payload, key);
            appendPayload(payload, "\"");
            appendPayload(payload, Constants.CRLF);
            appendPayload(payload, Constants.CRLF);
            appendPayload(payload, value.toString());
            appendPayload(payload, Constants.CRLF);
        }
    }

    private void appendPayload(ByteArrayBuffer payload, String content) {
        try {
            byte[] b = content.getBytes("utf-8");
            payload.append(b, 0, b.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String substringAfterLast(final String str, final String separator) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (separator == null || separator.isEmpty()) {
            return "";
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length()) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    public static byte[] toByteArray(final InputStream input) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(final InputStream input, final OutputStream output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(final InputStream input, final OutputStream output)
            throws IOException {
        return copy(input, output, 1024 * 4);
    }

    public static long copy(final InputStream input, final OutputStream output, final int bufferSize) throws IOException {
        return copyLarge(input, output, new byte[bufferSize]);
    }

    public static long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer)
            throws IOException {
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
