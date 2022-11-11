package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PositionValue {
    private Integer id;

    private Date createTime;

    private BigDecimal aX;

    private BigDecimal aY;

    private BigDecimal aZ;

    private BigDecimal angleX;

    private BigDecimal angleY;

    private BigDecimal angleZ;

    private BigDecimal wX;

    private BigDecimal wY;

    private BigDecimal wZ;

    private String hexadecimal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getaX() {
        return aX;
    }

    public void setaX(BigDecimal aX) {
        this.aX = aX;
    }

    public BigDecimal getaY() {
        return aY;
    }

    public void setaY(BigDecimal aY) {
        this.aY = aY;
    }

    public BigDecimal getaZ() {
        return aZ;
    }

    public void setaZ(BigDecimal aZ) {
        this.aZ = aZ;
    }

    public BigDecimal getAngleX() {
        return angleX;
    }

    public void setAngleX(BigDecimal angleX) {
        this.angleX = angleX;
    }

    public BigDecimal getAngleY() {
        return angleY;
    }

    public void setAngleY(BigDecimal angleY) {
        this.angleY = angleY;
    }

    public BigDecimal getAngleZ() {
        return angleZ;
    }

    public void setAngleZ(BigDecimal angleZ) {
        this.angleZ = angleZ;
    }

    public BigDecimal getwX() {
        return wX;
    }

    public void setwX(BigDecimal wX) {
        this.wX = wX;
    }

    public BigDecimal getwY() {
        return wY;
    }

    public void setwY(BigDecimal wY) {
        this.wY = wY;
    }

    public BigDecimal getwZ() {
        return wZ;
    }

    public void setwZ(BigDecimal wZ) {
        this.wZ = wZ;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", aX=").append(aX);
        sb.append(", aY=").append(aY);
        sb.append(", aZ=").append(aZ);
        sb.append(", angleX=").append(angleX);
        sb.append(", angleY=").append(angleY);
        sb.append(", angleZ=").append(angleZ);
        sb.append(", wX=").append(wX);
        sb.append(", wY=").append(wY);
        sb.append(", wZ=").append(wZ);
        sb.append(", hexadecimal=").append(hexadecimal);
        sb.append("]");
        return sb.toString();
    }
}