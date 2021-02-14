package net.kurien.blog.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;

public class CertificationUtil {
    private static final String KEY_SOURCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String createCertKey(HttpServletRequest request, String certType, String certId, int certKeyLength, int expirationMillisecondTime) {
        certType = "cert_" + certType;
        String certKey = createCertKey(certKeyLength);
        Date certExpirationTime = new Date(Calendar.getInstance().getTimeInMillis() + expirationMillisecondTime);

        HttpSession session = request.getSession();

        session.setAttribute(certType + "_id", certId);
        session.setAttribute(certType + "_key", certKey);
        session.setAttribute(certType + "_expirations", certExpirationTime);
        session.setAttribute(certType + "_certed", "");

        return certKey;
    }

    public static boolean checkCertKey(HttpServletRequest request, String certType, String checkCertId, String checkCertKey) {
        certType = "cert_" + certType;

        HttpSession session = request.getSession();

        String certId = (String) session.getAttribute(certType + "_id");
        String certKey = (String) session.getAttribute(certType + "_key");
        Date certExpirationTime = (Date) session.getAttribute(certType + "_expirations");

        if(certId.equals(checkCertId) == false) {
            return false;
        }

        if(certKey.equals(checkCertKey) == false) {
            return false;
        }

        if(Calendar.getInstance().getTimeInMillis() > certExpirationTime.getTime()) {
            return false;
        }

        session.removeAttribute(certType + "_id");
        session.removeAttribute(certType + "_key");
        session.removeAttribute(certType + "_expirations");

        session.setAttribute(certType + "_certed", certId);

        return true;
    }

    public static boolean checkCerted(HttpServletRequest request, String certType, String checkCertId) {
        certType = "cert_" + certType;

        HttpSession session = request.getSession();

        String certedId = (String) session.getAttribute(certType + "_certed");

        if(certedId.equals(checkCertId) == false) {
            return false;
        }

        return true;
    }

    public static void clearCert(HttpServletRequest request, String certType) {
        certType = "cert_" + certType;

        HttpSession session = request.getSession();

        if(session.getAttribute(certType + "_certed") == null) {
            return;
        }

        session.removeAttribute(certType + "_certed");
    }

    public static String createCertKey(int certKeyLength) {
        StringBuffer certKeySb = new StringBuffer();

        for(int i = 0; i < certKeyLength; i++) {
            int randomNumber = (int) (Math.random() * (double) KEY_SOURCE.length());
            certKeySb.append(KEY_SOURCE.charAt(randomNumber));
        }

        return certKeySb.toString();
    }
}
