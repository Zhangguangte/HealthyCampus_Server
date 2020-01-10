package com.muyou.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TbMedicineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbMedicineExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andC1IdIsNull() {
            addCriterion("c1_id is null");
            return (Criteria) this;
        }

        public Criteria andC1IdIsNotNull() {
            addCriterion("c1_id is not null");
            return (Criteria) this;
        }

        public Criteria andC1IdEqualTo(Integer value) {
            addCriterion("c1_id =", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdNotEqualTo(Integer value) {
            addCriterion("c1_id <>", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdGreaterThan(Integer value) {
            addCriterion("c1_id >", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("c1_id >=", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdLessThan(Integer value) {
            addCriterion("c1_id <", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdLessThanOrEqualTo(Integer value) {
            addCriterion("c1_id <=", value, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdIn(List<Integer> values) {
            addCriterion("c1_id in", values, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdNotIn(List<Integer> values) {
            addCriterion("c1_id not in", values, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdBetween(Integer value1, Integer value2) {
            addCriterion("c1_id between", value1, value2, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC1IdNotBetween(Integer value1, Integer value2) {
            addCriterion("c1_id not between", value1, value2, "c1Id");
            return (Criteria) this;
        }

        public Criteria andC2IdIsNull() {
            addCriterion("c2_id is null");
            return (Criteria) this;
        }

        public Criteria andC2IdIsNotNull() {
            addCriterion("c2_id is not null");
            return (Criteria) this;
        }

        public Criteria andC2IdEqualTo(Integer value) {
            addCriterion("c2_id =", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdNotEqualTo(Integer value) {
            addCriterion("c2_id <>", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdGreaterThan(Integer value) {
            addCriterion("c2_id >", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("c2_id >=", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdLessThan(Integer value) {
            addCriterion("c2_id <", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdLessThanOrEqualTo(Integer value) {
            addCriterion("c2_id <=", value, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdIn(List<Integer> values) {
            addCriterion("c2_id in", values, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdNotIn(List<Integer> values) {
            addCriterion("c2_id not in", values, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdBetween(Integer value1, Integer value2) {
            addCriterion("c2_id between", value1, value2, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("c2_id not between", value1, value2, "c2Id");
            return (Criteria) this;
        }

        public Criteria andC1IsNull() {
            addCriterion("c1 is null");
            return (Criteria) this;
        }

        public Criteria andC1IsNotNull() {
            addCriterion("c1 is not null");
            return (Criteria) this;
        }

        public Criteria andC1EqualTo(String value) {
            addCriterion("c1 =", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1NotEqualTo(String value) {
            addCriterion("c1 <>", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1GreaterThan(String value) {
            addCriterion("c1 >", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1GreaterThanOrEqualTo(String value) {
            addCriterion("c1 >=", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1LessThan(String value) {
            addCriterion("c1 <", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1LessThanOrEqualTo(String value) {
            addCriterion("c1 <=", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1Like(String value) {
            addCriterion("c1 like", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1NotLike(String value) {
            addCriterion("c1 not like", value, "c1");
            return (Criteria) this;
        }

        public Criteria andC1In(List<String> values) {
            addCriterion("c1 in", values, "c1");
            return (Criteria) this;
        }

        public Criteria andC1NotIn(List<String> values) {
            addCriterion("c1 not in", values, "c1");
            return (Criteria) this;
        }

        public Criteria andC1Between(String value1, String value2) {
            addCriterion("c1 between", value1, value2, "c1");
            return (Criteria) this;
        }

        public Criteria andC1NotBetween(String value1, String value2) {
            addCriterion("c1 not between", value1, value2, "c1");
            return (Criteria) this;
        }

        public Criteria andC2IsNull() {
            addCriterion("c2 is null");
            return (Criteria) this;
        }

        public Criteria andC2IsNotNull() {
            addCriterion("c2 is not null");
            return (Criteria) this;
        }

        public Criteria andC2EqualTo(String value) {
            addCriterion("c2 =", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2NotEqualTo(String value) {
            addCriterion("c2 <>", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2GreaterThan(String value) {
            addCriterion("c2 >", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2GreaterThanOrEqualTo(String value) {
            addCriterion("c2 >=", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2LessThan(String value) {
            addCriterion("c2 <", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2LessThanOrEqualTo(String value) {
            addCriterion("c2 <=", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2Like(String value) {
            addCriterion("c2 like", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2NotLike(String value) {
            addCriterion("c2 not like", value, "c2");
            return (Criteria) this;
        }

        public Criteria andC2In(List<String> values) {
            addCriterion("c2 in", values, "c2");
            return (Criteria) this;
        }

        public Criteria andC2NotIn(List<String> values) {
            addCriterion("c2 not in", values, "c2");
            return (Criteria) this;
        }

        public Criteria andC2Between(String value1, String value2) {
            addCriterion("c2 between", value1, value2, "c2");
            return (Criteria) this;
        }

        public Criteria andC2NotBetween(String value1, String value2) {
            addCriterion("c2 not between", value1, value2, "c2");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andPyCodeIsNull() {
            addCriterion("py_code is null");
            return (Criteria) this;
        }

        public Criteria andPyCodeIsNotNull() {
            addCriterion("py_code is not null");
            return (Criteria) this;
        }

        public Criteria andPyCodeEqualTo(String value) {
            addCriterion("py_code =", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeNotEqualTo(String value) {
            addCriterion("py_code <>", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeGreaterThan(String value) {
            addCriterion("py_code >", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("py_code >=", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeLessThan(String value) {
            addCriterion("py_code <", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeLessThanOrEqualTo(String value) {
            addCriterion("py_code <=", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeLike(String value) {
            addCriterion("py_code like", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeNotLike(String value) {
            addCriterion("py_code not like", value, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeIn(List<String> values) {
            addCriterion("py_code in", values, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeNotIn(List<String> values) {
            addCriterion("py_code not in", values, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeBetween(String value1, String value2) {
            addCriterion("py_code between", value1, value2, "pyCode");
            return (Criteria) this;
        }

        public Criteria andPyCodeNotBetween(String value1, String value2) {
            addCriterion("py_code not between", value1, value2, "pyCode");
            return (Criteria) this;
        }

        public Criteria andSpecIsNull() {
            addCriterion("spec is null");
            return (Criteria) this;
        }

        public Criteria andSpecIsNotNull() {
            addCriterion("spec is not null");
            return (Criteria) this;
        }

        public Criteria andSpecEqualTo(String value) {
            addCriterion("spec =", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotEqualTo(String value) {
            addCriterion("spec <>", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThan(String value) {
            addCriterion("spec >", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThanOrEqualTo(String value) {
            addCriterion("spec >=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThan(String value) {
            addCriterion("spec <", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThanOrEqualTo(String value) {
            addCriterion("spec <=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLike(String value) {
            addCriterion("spec like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotLike(String value) {
            addCriterion("spec not like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecIn(List<String> values) {
            addCriterion("spec in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotIn(List<String> values) {
            addCriterion("spec not in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecBetween(String value1, String value2) {
            addCriterion("spec between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotBetween(String value1, String value2) {
            addCriterion("spec not between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIsNull() {
            addCriterion("approval_number is null");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIsNotNull() {
            addCriterion("approval_number is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberEqualTo(String value) {
            addCriterion("approval_number =", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotEqualTo(String value) {
            addCriterion("approval_number <>", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberGreaterThan(String value) {
            addCriterion("approval_number >", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberGreaterThanOrEqualTo(String value) {
            addCriterion("approval_number >=", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLessThan(String value) {
            addCriterion("approval_number <", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLessThanOrEqualTo(String value) {
            addCriterion("approval_number <=", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLike(String value) {
            addCriterion("approval_number like", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotLike(String value) {
            addCriterion("approval_number not like", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIn(List<String> values) {
            addCriterion("approval_number in", values, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotIn(List<String> values) {
            addCriterion("approval_number not in", values, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberBetween(String value1, String value2) {
            addCriterion("approval_number between", value1, value2, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotBetween(String value1, String value2) {
            addCriterion("approval_number not between", value1, value2, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("bar_code is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("bar_code is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("bar_code =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("bar_code <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("bar_code >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bar_code >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("bar_code <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("bar_code <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("bar_code like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("bar_code not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("bar_code in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("bar_code not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("bar_code between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("bar_code not between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andZhuzhiIsNull() {
            addCriterion("zhuzhi is null");
            return (Criteria) this;
        }

        public Criteria andZhuzhiIsNotNull() {
            addCriterion("zhuzhi is not null");
            return (Criteria) this;
        }

        public Criteria andZhuzhiEqualTo(String value) {
            addCriterion("zhuzhi =", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiNotEqualTo(String value) {
            addCriterion("zhuzhi <>", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiGreaterThan(String value) {
            addCriterion("zhuzhi >", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiGreaterThanOrEqualTo(String value) {
            addCriterion("zhuzhi >=", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiLessThan(String value) {
            addCriterion("zhuzhi <", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiLessThanOrEqualTo(String value) {
            addCriterion("zhuzhi <=", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiLike(String value) {
            addCriterion("zhuzhi like", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiNotLike(String value) {
            addCriterion("zhuzhi not like", value, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiIn(List<String> values) {
            addCriterion("zhuzhi in", values, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiNotIn(List<String> values) {
            addCriterion("zhuzhi not in", values, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiBetween(String value1, String value2) {
            addCriterion("zhuzhi between", value1, value2, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andZhuzhiNotBetween(String value1, String value2) {
            addCriterion("zhuzhi not between", value1, value2, "zhuzhi");
            return (Criteria) this;
        }

        public Criteria andExplainBookIsNull() {
            addCriterion("explain_book is null");
            return (Criteria) this;
        }

        public Criteria andExplainBookIsNotNull() {
            addCriterion("explain_book is not null");
            return (Criteria) this;
        }

        public Criteria andExplainBookEqualTo(String value) {
            addCriterion("explain_book =", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookNotEqualTo(String value) {
            addCriterion("explain_book <>", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookGreaterThan(String value) {
            addCriterion("explain_book >", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookGreaterThanOrEqualTo(String value) {
            addCriterion("explain_book >=", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookLessThan(String value) {
            addCriterion("explain_book <", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookLessThanOrEqualTo(String value) {
            addCriterion("explain_book <=", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookLike(String value) {
            addCriterion("explain_book like", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookNotLike(String value) {
            addCriterion("explain_book not like", value, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookIn(List<String> values) {
            addCriterion("explain_book in", values, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookNotIn(List<String> values) {
            addCriterion("explain_book not in", values, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookBetween(String value1, String value2) {
            addCriterion("explain_book between", value1, value2, "explainBook");
            return (Criteria) this;
        }

        public Criteria andExplainBookNotBetween(String value1, String value2) {
            addCriterion("explain_book not between", value1, value2, "explainBook");
            return (Criteria) this;
        }

        public Criteria andReplenishIsNull() {
            addCriterion("replenish is null");
            return (Criteria) this;
        }

        public Criteria andReplenishIsNotNull() {
            addCriterion("replenish is not null");
            return (Criteria) this;
        }

        public Criteria andReplenishEqualTo(String value) {
            addCriterion("replenish =", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishNotEqualTo(String value) {
            addCriterion("replenish <>", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishGreaterThan(String value) {
            addCriterion("replenish >", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishGreaterThanOrEqualTo(String value) {
            addCriterion("replenish >=", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishLessThan(String value) {
            addCriterion("replenish <", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishLessThanOrEqualTo(String value) {
            addCriterion("replenish <=", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishLike(String value) {
            addCriterion("replenish like", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishNotLike(String value) {
            addCriterion("replenish not like", value, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishIn(List<String> values) {
            addCriterion("replenish in", values, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishNotIn(List<String> values) {
            addCriterion("replenish not in", values, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishBetween(String value1, String value2) {
            addCriterion("replenish between", value1, value2, "replenish");
            return (Criteria) this;
        }

        public Criteria andReplenishNotBetween(String value1, String value2) {
            addCriterion("replenish not between", value1, value2, "replenish");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andIsOtcIsNull() {
            addCriterion("is_otc is null");
            return (Criteria) this;
        }

        public Criteria andIsOtcIsNotNull() {
            addCriterion("is_otc is not null");
            return (Criteria) this;
        }

        public Criteria andIsOtcEqualTo(Integer value) {
            addCriterion("is_otc =", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcNotEqualTo(Integer value) {
            addCriterion("is_otc <>", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcGreaterThan(Integer value) {
            addCriterion("is_otc >", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_otc >=", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcLessThan(Integer value) {
            addCriterion("is_otc <", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcLessThanOrEqualTo(Integer value) {
            addCriterion("is_otc <=", value, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcIn(List<Integer> values) {
            addCriterion("is_otc in", values, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcNotIn(List<Integer> values) {
            addCriterion("is_otc not in", values, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcBetween(Integer value1, Integer value2) {
            addCriterion("is_otc between", value1, value2, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsOtcNotBetween(Integer value1, Integer value2) {
            addCriterion("is_otc not between", value1, value2, "isOtc");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNull() {
            addCriterion("c_time is null");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNotNull() {
            addCriterion("c_time is not null");
            return (Criteria) this;
        }

        public Criteria andCTimeEqualTo(String value) {
            addCriterion("c_time =", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotEqualTo(String value) {
            addCriterion("c_time <>", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThan(String value) {
            addCriterion("c_time >", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThanOrEqualTo(String value) {
            addCriterion("c_time >=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThan(String value) {
            addCriterion("c_time <", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThanOrEqualTo(String value) {
            addCriterion("c_time <=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLike(String value) {
            addCriterion("c_time like", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotLike(String value) {
            addCriterion("c_time not like", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeIn(List<String> values) {
            addCriterion("c_time in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotIn(List<String> values) {
            addCriterion("c_time not in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeBetween(String value1, String value2) {
            addCriterion("c_time between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotBetween(String value1, String value2) {
            addCriterion("c_time not between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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