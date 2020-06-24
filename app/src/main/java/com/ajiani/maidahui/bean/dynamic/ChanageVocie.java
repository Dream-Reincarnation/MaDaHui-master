package com.ajiani.maidahui.bean.dynamic;

public class ChanageVocie {
    int unsel;
    int sel;
    int type;
    String name;
    boolean issel;
    public ChanageVocie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIssel() {
        return issel;
    }

    public void setIssel(boolean issel) {
        this.issel = issel;
    }

    public ChanageVocie(int unsel, int sel, int type, String name, boolean issel) {
        this.unsel = unsel;
        this.sel = sel;
        this.type = type;
        this.name = name;
        this.issel = issel;
    }

    public int getUnsel() {
        return unsel;
    }

    public void setUnsel(int unsel) {
        this.unsel = unsel;
    }

    public int getSel() {
        return sel;
    }

    public void setSel(int sel) {
        this.sel = sel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
