package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionValueExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andAXIsNull() {
            addCriterion("a_x is null");
            return (Criteria) this;
        }

        public Criteria andAXIsNotNull() {
            addCriterion("a_x is not null");
            return (Criteria) this;
        }

        public Criteria andAXEqualTo(BigDecimal value) {
            addCriterion("a_x =", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXNotEqualTo(BigDecimal value) {
            addCriterion("a_x <>", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXGreaterThan(BigDecimal value) {
            addCriterion("a_x >", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("a_x >=", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXLessThan(BigDecimal value) {
            addCriterion("a_x <", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXLessThanOrEqualTo(BigDecimal value) {
            addCriterion("a_x <=", value, "aX");
            return (Criteria) this;
        }

        public Criteria andAXIn(List<BigDecimal> values) {
            addCriterion("a_x in", values, "aX");
            return (Criteria) this;
        }

        public Criteria andAXNotIn(List<BigDecimal> values) {
            addCriterion("a_x not in", values, "aX");
            return (Criteria) this;
        }

        public Criteria andAXBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_x between", value1, value2, "aX");
            return (Criteria) this;
        }

        public Criteria andAXNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_x not between", value1, value2, "aX");
            return (Criteria) this;
        }

        public Criteria andAYIsNull() {
            addCriterion("a_y is null");
            return (Criteria) this;
        }

        public Criteria andAYIsNotNull() {
            addCriterion("a_y is not null");
            return (Criteria) this;
        }

        public Criteria andAYEqualTo(BigDecimal value) {
            addCriterion("a_y =", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYNotEqualTo(BigDecimal value) {
            addCriterion("a_y <>", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYGreaterThan(BigDecimal value) {
            addCriterion("a_y >", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("a_y >=", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYLessThan(BigDecimal value) {
            addCriterion("a_y <", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYLessThanOrEqualTo(BigDecimal value) {
            addCriterion("a_y <=", value, "aY");
            return (Criteria) this;
        }

        public Criteria andAYIn(List<BigDecimal> values) {
            addCriterion("a_y in", values, "aY");
            return (Criteria) this;
        }

        public Criteria andAYNotIn(List<BigDecimal> values) {
            addCriterion("a_y not in", values, "aY");
            return (Criteria) this;
        }

        public Criteria andAYBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_y between", value1, value2, "aY");
            return (Criteria) this;
        }

        public Criteria andAYNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_y not between", value1, value2, "aY");
            return (Criteria) this;
        }

        public Criteria andAZIsNull() {
            addCriterion("a_z is null");
            return (Criteria) this;
        }

        public Criteria andAZIsNotNull() {
            addCriterion("a_z is not null");
            return (Criteria) this;
        }

        public Criteria andAZEqualTo(BigDecimal value) {
            addCriterion("a_z =", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZNotEqualTo(BigDecimal value) {
            addCriterion("a_z <>", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZGreaterThan(BigDecimal value) {
            addCriterion("a_z >", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("a_z >=", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZLessThan(BigDecimal value) {
            addCriterion("a_z <", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZLessThanOrEqualTo(BigDecimal value) {
            addCriterion("a_z <=", value, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZIn(List<BigDecimal> values) {
            addCriterion("a_z in", values, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZNotIn(List<BigDecimal> values) {
            addCriterion("a_z not in", values, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_z between", value1, value2, "aZ");
            return (Criteria) this;
        }

        public Criteria andAZNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a_z not between", value1, value2, "aZ");
            return (Criteria) this;
        }

        public Criteria andAngleXIsNull() {
            addCriterion("angle_x is null");
            return (Criteria) this;
        }

        public Criteria andAngleXIsNotNull() {
            addCriterion("angle_x is not null");
            return (Criteria) this;
        }

        public Criteria andAngleXEqualTo(BigDecimal value) {
            addCriterion("angle_x =", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXNotEqualTo(BigDecimal value) {
            addCriterion("angle_x <>", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXGreaterThan(BigDecimal value) {
            addCriterion("angle_x >", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_x >=", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXLessThan(BigDecimal value) {
            addCriterion("angle_x <", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXLessThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_x <=", value, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXIn(List<BigDecimal> values) {
            addCriterion("angle_x in", values, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXNotIn(List<BigDecimal> values) {
            addCriterion("angle_x not in", values, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_x between", value1, value2, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleXNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_x not between", value1, value2, "angleX");
            return (Criteria) this;
        }

        public Criteria andAngleYIsNull() {
            addCriterion("angle_y is null");
            return (Criteria) this;
        }

        public Criteria andAngleYIsNotNull() {
            addCriterion("angle_y is not null");
            return (Criteria) this;
        }

        public Criteria andAngleYEqualTo(BigDecimal value) {
            addCriterion("angle_y =", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYNotEqualTo(BigDecimal value) {
            addCriterion("angle_y <>", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYGreaterThan(BigDecimal value) {
            addCriterion("angle_y >", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_y >=", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYLessThan(BigDecimal value) {
            addCriterion("angle_y <", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYLessThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_y <=", value, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYIn(List<BigDecimal> values) {
            addCriterion("angle_y in", values, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYNotIn(List<BigDecimal> values) {
            addCriterion("angle_y not in", values, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_y between", value1, value2, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleYNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_y not between", value1, value2, "angleY");
            return (Criteria) this;
        }

        public Criteria andAngleZIsNull() {
            addCriterion("angle_z is null");
            return (Criteria) this;
        }

        public Criteria andAngleZIsNotNull() {
            addCriterion("angle_z is not null");
            return (Criteria) this;
        }

        public Criteria andAngleZEqualTo(BigDecimal value) {
            addCriterion("angle_z =", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZNotEqualTo(BigDecimal value) {
            addCriterion("angle_z <>", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZGreaterThan(BigDecimal value) {
            addCriterion("angle_z >", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_z >=", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZLessThan(BigDecimal value) {
            addCriterion("angle_z <", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZLessThanOrEqualTo(BigDecimal value) {
            addCriterion("angle_z <=", value, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZIn(List<BigDecimal> values) {
            addCriterion("angle_z in", values, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZNotIn(List<BigDecimal> values) {
            addCriterion("angle_z not in", values, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_z between", value1, value2, "angleZ");
            return (Criteria) this;
        }

        public Criteria andAngleZNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("angle_z not between", value1, value2, "angleZ");
            return (Criteria) this;
        }

        public Criteria andWXIsNull() {
            addCriterion("w_x is null");
            return (Criteria) this;
        }

        public Criteria andWXIsNotNull() {
            addCriterion("w_x is not null");
            return (Criteria) this;
        }

        public Criteria andWXEqualTo(BigDecimal value) {
            addCriterion("w_x =", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXNotEqualTo(BigDecimal value) {
            addCriterion("w_x <>", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXGreaterThan(BigDecimal value) {
            addCriterion("w_x >", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("w_x >=", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXLessThan(BigDecimal value) {
            addCriterion("w_x <", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXLessThanOrEqualTo(BigDecimal value) {
            addCriterion("w_x <=", value, "wX");
            return (Criteria) this;
        }

        public Criteria andWXIn(List<BigDecimal> values) {
            addCriterion("w_x in", values, "wX");
            return (Criteria) this;
        }

        public Criteria andWXNotIn(List<BigDecimal> values) {
            addCriterion("w_x not in", values, "wX");
            return (Criteria) this;
        }

        public Criteria andWXBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_x between", value1, value2, "wX");
            return (Criteria) this;
        }

        public Criteria andWXNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_x not between", value1, value2, "wX");
            return (Criteria) this;
        }

        public Criteria andWYIsNull() {
            addCriterion("w_y is null");
            return (Criteria) this;
        }

        public Criteria andWYIsNotNull() {
            addCriterion("w_y is not null");
            return (Criteria) this;
        }

        public Criteria andWYEqualTo(BigDecimal value) {
            addCriterion("w_y =", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYNotEqualTo(BigDecimal value) {
            addCriterion("w_y <>", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYGreaterThan(BigDecimal value) {
            addCriterion("w_y >", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("w_y >=", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYLessThan(BigDecimal value) {
            addCriterion("w_y <", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYLessThanOrEqualTo(BigDecimal value) {
            addCriterion("w_y <=", value, "wY");
            return (Criteria) this;
        }

        public Criteria andWYIn(List<BigDecimal> values) {
            addCriterion("w_y in", values, "wY");
            return (Criteria) this;
        }

        public Criteria andWYNotIn(List<BigDecimal> values) {
            addCriterion("w_y not in", values, "wY");
            return (Criteria) this;
        }

        public Criteria andWYBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_y between", value1, value2, "wY");
            return (Criteria) this;
        }

        public Criteria andWYNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_y not between", value1, value2, "wY");
            return (Criteria) this;
        }

        public Criteria andWZIsNull() {
            addCriterion("w_z is null");
            return (Criteria) this;
        }

        public Criteria andWZIsNotNull() {
            addCriterion("w_z is not null");
            return (Criteria) this;
        }

        public Criteria andWZEqualTo(BigDecimal value) {
            addCriterion("w_z =", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZNotEqualTo(BigDecimal value) {
            addCriterion("w_z <>", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZGreaterThan(BigDecimal value) {
            addCriterion("w_z >", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("w_z >=", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZLessThan(BigDecimal value) {
            addCriterion("w_z <", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZLessThanOrEqualTo(BigDecimal value) {
            addCriterion("w_z <=", value, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZIn(List<BigDecimal> values) {
            addCriterion("w_z in", values, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZNotIn(List<BigDecimal> values) {
            addCriterion("w_z not in", values, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_z between", value1, value2, "wZ");
            return (Criteria) this;
        }

        public Criteria andWZNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_z not between", value1, value2, "wZ");
            return (Criteria) this;
        }

        public Criteria andHexadecimalIsNull() {
            addCriterion("hexadecimal is null");
            return (Criteria) this;
        }

        public Criteria andHexadecimalIsNotNull() {
            addCriterion("hexadecimal is not null");
            return (Criteria) this;
        }

        public Criteria andHexadecimalEqualTo(String value) {
            addCriterion("hexadecimal =", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalNotEqualTo(String value) {
            addCriterion("hexadecimal <>", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalGreaterThan(String value) {
            addCriterion("hexadecimal >", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalGreaterThanOrEqualTo(String value) {
            addCriterion("hexadecimal >=", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalLessThan(String value) {
            addCriterion("hexadecimal <", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalLessThanOrEqualTo(String value) {
            addCriterion("hexadecimal <=", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalLike(String value) {
            addCriterion("hexadecimal like", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalNotLike(String value) {
            addCriterion("hexadecimal not like", value, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalIn(List<String> values) {
            addCriterion("hexadecimal in", values, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalNotIn(List<String> values) {
            addCriterion("hexadecimal not in", values, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalBetween(String value1, String value2) {
            addCriterion("hexadecimal between", value1, value2, "hexadecimal");
            return (Criteria) this;
        }

        public Criteria andHexadecimalNotBetween(String value1, String value2) {
            addCriterion("hexadecimal not between", value1, value2, "hexadecimal");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}