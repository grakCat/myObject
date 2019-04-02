package org.gars.mars.disruptor;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class SendMessage {

    private int sid;

    private String name;

    private String address;

    public SendMessage(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
