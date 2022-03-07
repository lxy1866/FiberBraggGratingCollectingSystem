package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NormalRangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NormalRangeExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueIsNull() {
            addCriterion("val1_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueIsNotNull() {
            addCriterion("val1_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueEqualTo(BigDecimal value) {
            addCriterion("val1_min_value =", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val1_min_value <>", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueGreaterThan(BigDecimal value) {
            addCriterion("val1_min_value >", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val1_min_value >=", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueLessThan(BigDecimal value) {
            addCriterion("val1_min_value <", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val1_min_value <=", value, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueIn(List<BigDecimal> values) {
            addCriterion("val1_min_value in", values, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val1_min_value not in", values, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1_min_value between", value1, value2, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1_min_value not between", value1, value2, "val1MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueIsNull() {
            addCriterion("val2_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueIsNotNull() {
            addCriterion("val2_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueEqualTo(BigDecimal value) {
            addCriterion("val2_min_value =", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val2_min_value <>", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueGreaterThan(BigDecimal value) {
            addCriterion("val2_min_value >", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val2_min_value >=", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueLessThan(BigDecimal value) {
            addCriterion("val2_min_value <", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val2_min_value <=", value, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueIn(List<BigDecimal> values) {
            addCriterion("val2_min_value in", values, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val2_min_value not in", values, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2_min_value between", value1, value2, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal2MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2_min_value not between", value1, value2, "val2MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueIsNull() {
            addCriterion("val3_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueIsNotNull() {
            addCriterion("val3_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueEqualTo(BigDecimal value) {
            addCriterion("val3_min_value =", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val3_min_value <>", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueGreaterThan(BigDecimal value) {
            addCriterion("val3_min_value >", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val3_min_value >=", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueLessThan(BigDecimal value) {
            addCriterion("val3_min_value <", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val3_min_value <=", value, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueIn(List<BigDecimal> values) {
            addCriterion("val3_min_value in", values, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val3_min_value not in", values, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3_min_value between", value1, value2, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal3MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3_min_value not between", value1, value2, "val3MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueIsNull() {
            addCriterion("val4_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueIsNotNull() {
            addCriterion("val4_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueEqualTo(BigDecimal value) {
            addCriterion("val4_min_value =", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val4_min_value <>", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueGreaterThan(BigDecimal value) {
            addCriterion("val4_min_value >", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val4_min_value >=", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueLessThan(BigDecimal value) {
            addCriterion("val4_min_value <", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val4_min_value <=", value, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueIn(List<BigDecimal> values) {
            addCriterion("val4_min_value in", values, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val4_min_value not in", values, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4_min_value between", value1, value2, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal4MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4_min_value not between", value1, value2, "val4MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueIsNull() {
            addCriterion("val5_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueIsNotNull() {
            addCriterion("val5_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueEqualTo(BigDecimal value) {
            addCriterion("val5_min_value =", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val5_min_value <>", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueGreaterThan(BigDecimal value) {
            addCriterion("val5_min_value >", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val5_min_value >=", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueLessThan(BigDecimal value) {
            addCriterion("val5_min_value <", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val5_min_value <=", value, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueIn(List<BigDecimal> values) {
            addCriterion("val5_min_value in", values, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val5_min_value not in", values, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5_min_value between", value1, value2, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal5MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5_min_value not between", value1, value2, "val5MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueIsNull() {
            addCriterion("val6_min_value is null");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueIsNotNull() {
            addCriterion("val6_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueEqualTo(BigDecimal value) {
            addCriterion("val6_min_value =", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueNotEqualTo(BigDecimal value) {
            addCriterion("val6_min_value <>", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueGreaterThan(BigDecimal value) {
            addCriterion("val6_min_value >", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val6_min_value >=", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueLessThan(BigDecimal value) {
            addCriterion("val6_min_value <", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val6_min_value <=", value, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueIn(List<BigDecimal> values) {
            addCriterion("val6_min_value in", values, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueNotIn(List<BigDecimal> values) {
            addCriterion("val6_min_value not in", values, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6_min_value between", value1, value2, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal6MinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6_min_value not between", value1, value2, "val6MinValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueIsNull() {
            addCriterion("val1_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueIsNotNull() {
            addCriterion("val1_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueEqualTo(BigDecimal value) {
            addCriterion("val1_max_value =", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val1_max_value <>", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val1_max_value >", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val1_max_value >=", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueLessThan(BigDecimal value) {
            addCriterion("val1_max_value <", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val1_max_value <=", value, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueIn(List<BigDecimal> values) {
            addCriterion("val1_max_value in", values, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val1_max_value not in", values, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1_max_value between", value1, value2, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal1MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1_max_value not between", value1, value2, "val1MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueIsNull() {
            addCriterion("val2_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueIsNotNull() {
            addCriterion("val2_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueEqualTo(BigDecimal value) {
            addCriterion("val2_max_value =", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val2_max_value <>", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val2_max_value >", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val2_max_value >=", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueLessThan(BigDecimal value) {
            addCriterion("val2_max_value <", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val2_max_value <=", value, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueIn(List<BigDecimal> values) {
            addCriterion("val2_max_value in", values, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val2_max_value not in", values, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2_max_value between", value1, value2, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal2MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2_max_value not between", value1, value2, "val2MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueIsNull() {
            addCriterion("val3_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueIsNotNull() {
            addCriterion("val3_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueEqualTo(BigDecimal value) {
            addCriterion("val3_max_value =", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val3_max_value <>", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val3_max_value >", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val3_max_value >=", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueLessThan(BigDecimal value) {
            addCriterion("val3_max_value <", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val3_max_value <=", value, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueIn(List<BigDecimal> values) {
            addCriterion("val3_max_value in", values, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val3_max_value not in", values, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3_max_value between", value1, value2, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal3MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3_max_value not between", value1, value2, "val3MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueIsNull() {
            addCriterion("val4_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueIsNotNull() {
            addCriterion("val4_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueEqualTo(BigDecimal value) {
            addCriterion("val4_max_value =", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val4_max_value <>", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val4_max_value >", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val4_max_value >=", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueLessThan(BigDecimal value) {
            addCriterion("val4_max_value <", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val4_max_value <=", value, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueIn(List<BigDecimal> values) {
            addCriterion("val4_max_value in", values, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val4_max_value not in", values, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4_max_value between", value1, value2, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal4MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4_max_value not between", value1, value2, "val4MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueIsNull() {
            addCriterion("val5_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueIsNotNull() {
            addCriterion("val5_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueEqualTo(BigDecimal value) {
            addCriterion("val5_max_value =", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val5_max_value <>", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val5_max_value >", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val5_max_value >=", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueLessThan(BigDecimal value) {
            addCriterion("val5_max_value <", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val5_max_value <=", value, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueIn(List<BigDecimal> values) {
            addCriterion("val5_max_value in", values, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val5_max_value not in", values, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5_max_value between", value1, value2, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal5MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5_max_value not between", value1, value2, "val5MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueIsNull() {
            addCriterion("val6_max_value is null");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueIsNotNull() {
            addCriterion("val6_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueEqualTo(BigDecimal value) {
            addCriterion("val6_max_value =", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueNotEqualTo(BigDecimal value) {
            addCriterion("val6_max_value <>", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueGreaterThan(BigDecimal value) {
            addCriterion("val6_max_value >", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val6_max_value >=", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueLessThan(BigDecimal value) {
            addCriterion("val6_max_value <", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("val6_max_value <=", value, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueIn(List<BigDecimal> values) {
            addCriterion("val6_max_value in", values, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueNotIn(List<BigDecimal> values) {
            addCriterion("val6_max_value not in", values, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6_max_value between", value1, value2, "val6MaxValue");
            return (Criteria) this;
        }

        public Criteria andVal6MaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6_max_value not between", value1, value2, "val6MaxValue");
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

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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