package com.muyou.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbDiseaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbDiseaseExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNull() {
            addCriterion("symbol is null");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNotNull() {
            addCriterion("symbol is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolEqualTo(String value) {
            addCriterion("symbol =", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotEqualTo(String value) {
            addCriterion("symbol <>", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThan(String value) {
            addCriterion("symbol >", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("symbol >=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThan(String value) {
            addCriterion("symbol <", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThanOrEqualTo(String value) {
            addCriterion("symbol <=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLike(String value) {
            addCriterion("symbol like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotLike(String value) {
            addCriterion("symbol not like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolIn(List<String> values) {
            addCriterion("symbol in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotIn(List<String> values) {
            addCriterion("symbol not in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolBetween(String value1, String value2) {
            addCriterion("symbol between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotBetween(String value1, String value2) {
            addCriterion("symbol not between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andContagiousIsNull() {
            addCriterion("contagious is null");
            return (Criteria) this;
        }

        public Criteria andContagiousIsNotNull() {
            addCriterion("contagious is not null");
            return (Criteria) this;
        }

        public Criteria andContagiousEqualTo(String value) {
            addCriterion("contagious =", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousNotEqualTo(String value) {
            addCriterion("contagious <>", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousGreaterThan(String value) {
            addCriterion("contagious >", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousGreaterThanOrEqualTo(String value) {
            addCriterion("contagious >=", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousLessThan(String value) {
            addCriterion("contagious <", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousLessThanOrEqualTo(String value) {
            addCriterion("contagious <=", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousLike(String value) {
            addCriterion("contagious like", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousNotLike(String value) {
            addCriterion("contagious not like", value, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousIn(List<String> values) {
            addCriterion("contagious in", values, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousNotIn(List<String> values) {
            addCriterion("contagious not in", values, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousBetween(String value1, String value2) {
            addCriterion("contagious between", value1, value2, "contagious");
            return (Criteria) this;
        }

        public Criteria andContagiousNotBetween(String value1, String value2) {
            addCriterion("contagious not between", value1, value2, "contagious");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("rate like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("rate not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andDepartIsNull() {
            addCriterion("depart is null");
            return (Criteria) this;
        }

        public Criteria andDepartIsNotNull() {
            addCriterion("depart is not null");
            return (Criteria) this;
        }

        public Criteria andDepartEqualTo(String value) {
            addCriterion("depart =", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotEqualTo(String value) {
            addCriterion("depart <>", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartGreaterThan(String value) {
            addCriterion("depart >", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartGreaterThanOrEqualTo(String value) {
            addCriterion("depart >=", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLessThan(String value) {
            addCriterion("depart <", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLessThanOrEqualTo(String value) {
            addCriterion("depart <=", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLike(String value) {
            addCriterion("depart like", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotLike(String value) {
            addCriterion("depart not like", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartIn(List<String> values) {
            addCriterion("depart in", values, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotIn(List<String> values) {
            addCriterion("depart not in", values, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartBetween(String value1, String value2) {
            addCriterion("depart between", value1, value2, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotBetween(String value1, String value2) {
            addCriterion("depart not between", value1, value2, "depart");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(String value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(String value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(String value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(String value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(String value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(String value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLike(String value) {
            addCriterion("cost like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotLike(String value) {
            addCriterion("cost not like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<String> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<String> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(String value1, String value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(String value1, String value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andWayIsNull() {
            addCriterion("way is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("way is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(String value) {
            addCriterion("way =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(String value) {
            addCriterion("way <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(String value) {
            addCriterion("way >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(String value) {
            addCriterion("way >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(String value) {
            addCriterion("way <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(String value) {
            addCriterion("way <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLike(String value) {
            addCriterion("way like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotLike(String value) {
            addCriterion("way not like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<String> values) {
            addCriterion("way in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<String> values) {
            addCriterion("way not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(String value1, String value2) {
            addCriterion("way between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(String value1, String value2) {
            addCriterion("way not between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andDrugIsNull() {
            addCriterion("drug is null");
            return (Criteria) this;
        }

        public Criteria andDrugIsNotNull() {
            addCriterion("drug is not null");
            return (Criteria) this;
        }

        public Criteria andDrugEqualTo(String value) {
            addCriterion("drug =", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugNotEqualTo(String value) {
            addCriterion("drug <>", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugGreaterThan(String value) {
            addCriterion("drug >", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugGreaterThanOrEqualTo(String value) {
            addCriterion("drug >=", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugLessThan(String value) {
            addCriterion("drug <", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugLessThanOrEqualTo(String value) {
            addCriterion("drug <=", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugLike(String value) {
            addCriterion("drug like", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugNotLike(String value) {
            addCriterion("drug not like", value, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugIn(List<String> values) {
            addCriterion("drug in", values, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugNotIn(List<String> values) {
            addCriterion("drug not in", values, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugBetween(String value1, String value2) {
            addCriterion("drug between", value1, value2, "drug");
            return (Criteria) this;
        }

        public Criteria andDrugNotBetween(String value1, String value2) {
            addCriterion("drug not between", value1, value2, "drug");
            return (Criteria) this;
        }

        public Criteria andPartIsNull() {
            addCriterion("part is null");
            return (Criteria) this;
        }

        public Criteria andPartIsNotNull() {
            addCriterion("part is not null");
            return (Criteria) this;
        }

        public Criteria andPartEqualTo(String value) {
            addCriterion("part =", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotEqualTo(String value) {
            addCriterion("part <>", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThan(String value) {
            addCriterion("part >", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThanOrEqualTo(String value) {
            addCriterion("part >=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThan(String value) {
            addCriterion("part <", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThanOrEqualTo(String value) {
            addCriterion("part <=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLike(String value) {
            addCriterion("part like", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotLike(String value) {
            addCriterion("part not like", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartIn(List<String> values) {
            addCriterion("part in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotIn(List<String> values) {
            addCriterion("part not in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartBetween(String value1, String value2) {
            addCriterion("part between", value1, value2, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotBetween(String value1, String value2) {
            addCriterion("part not between", value1, value2, "part");
            return (Criteria) this;
        }

        public Criteria andInsuranceIsNull() {
            addCriterion("insurance is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIsNotNull() {
            addCriterion("insurance is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceEqualTo(String value) {
            addCriterion("insurance =", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotEqualTo(String value) {
            addCriterion("insurance <>", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceGreaterThan(String value) {
            addCriterion("insurance >", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceGreaterThanOrEqualTo(String value) {
            addCriterion("insurance >=", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceLessThan(String value) {
            addCriterion("insurance <", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceLessThanOrEqualTo(String value) {
            addCriterion("insurance <=", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceLike(String value) {
            addCriterion("insurance like", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotLike(String value) {
            addCriterion("insurance not like", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceIn(List<String> values) {
            addCriterion("insurance in", values, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotIn(List<String> values) {
            addCriterion("insurance not in", values, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceBetween(String value1, String value2) {
            addCriterion("insurance between", value1, value2, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotBetween(String value1, String value2) {
            addCriterion("insurance not between", value1, value2, "insurance");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andComplicationIsNull() {
            addCriterion("complication is null");
            return (Criteria) this;
        }

        public Criteria andComplicationIsNotNull() {
            addCriterion("complication is not null");
            return (Criteria) this;
        }

        public Criteria andComplicationEqualTo(String value) {
            addCriterion("complication =", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationNotEqualTo(String value) {
            addCriterion("complication <>", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationGreaterThan(String value) {
            addCriterion("complication >", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationGreaterThanOrEqualTo(String value) {
            addCriterion("complication >=", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationLessThan(String value) {
            addCriterion("complication <", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationLessThanOrEqualTo(String value) {
            addCriterion("complication <=", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationLike(String value) {
            addCriterion("complication like", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationNotLike(String value) {
            addCriterion("complication not like", value, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationIn(List<String> values) {
            addCriterion("complication in", values, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationNotIn(List<String> values) {
            addCriterion("complication not in", values, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationBetween(String value1, String value2) {
            addCriterion("complication between", value1, value2, "complication");
            return (Criteria) this;
        }

        public Criteria andComplicationNotBetween(String value1, String value2) {
            addCriterion("complication not between", value1, value2, "complication");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNull() {
            addCriterion("population is null");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNotNull() {
            addCriterion("population is not null");
            return (Criteria) this;
        }

        public Criteria andPopulationEqualTo(String value) {
            addCriterion("population =", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotEqualTo(String value) {
            addCriterion("population <>", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThan(String value) {
            addCriterion("population >", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThanOrEqualTo(String value) {
            addCriterion("population >=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThan(String value) {
            addCriterion("population <", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThanOrEqualTo(String value) {
            addCriterion("population <=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLike(String value) {
            addCriterion("population like", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotLike(String value) {
            addCriterion("population not like", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationIn(List<String> values) {
            addCriterion("population in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotIn(List<String> values) {
            addCriterion("population not in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationBetween(String value1, String value2) {
            addCriterion("population between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotBetween(String value1, String value2) {
            addCriterion("population not between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andSymptomsIsNull() {
            addCriterion("symptoms is null");
            return (Criteria) this;
        }

        public Criteria andSymptomsIsNotNull() {
            addCriterion("symptoms is not null");
            return (Criteria) this;
        }

        public Criteria andSymptomsEqualTo(String value) {
            addCriterion("symptoms =", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsNotEqualTo(String value) {
            addCriterion("symptoms <>", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsGreaterThan(String value) {
            addCriterion("symptoms >", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsGreaterThanOrEqualTo(String value) {
            addCriterion("symptoms >=", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsLessThan(String value) {
            addCriterion("symptoms <", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsLessThanOrEqualTo(String value) {
            addCriterion("symptoms <=", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsLike(String value) {
            addCriterion("symptoms like", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsNotLike(String value) {
            addCriterion("symptoms not like", value, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsIn(List<String> values) {
            addCriterion("symptoms in", values, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsNotIn(List<String> values) {
            addCriterion("symptoms not in", values, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsBetween(String value1, String value2) {
            addCriterion("symptoms between", value1, value2, "symptoms");
            return (Criteria) this;
        }

        public Criteria andSymptomsNotBetween(String value1, String value2) {
            addCriterion("symptoms not between", value1, value2, "symptoms");
            return (Criteria) this;
        }

        public Criteria andPreventionIsNull() {
            addCriterion("prevention is null");
            return (Criteria) this;
        }

        public Criteria andPreventionIsNotNull() {
            addCriterion("prevention is not null");
            return (Criteria) this;
        }

        public Criteria andPreventionEqualTo(String value) {
            addCriterion("prevention =", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionNotEqualTo(String value) {
            addCriterion("prevention <>", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionGreaterThan(String value) {
            addCriterion("prevention >", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionGreaterThanOrEqualTo(String value) {
            addCriterion("prevention >=", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionLessThan(String value) {
            addCriterion("prevention <", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionLessThanOrEqualTo(String value) {
            addCriterion("prevention <=", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionLike(String value) {
            addCriterion("prevention like", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionNotLike(String value) {
            addCriterion("prevention not like", value, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionIn(List<String> values) {
            addCriterion("prevention in", values, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionNotIn(List<String> values) {
            addCriterion("prevention not in", values, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionBetween(String value1, String value2) {
            addCriterion("prevention between", value1, value2, "prevention");
            return (Criteria) this;
        }

        public Criteria andPreventionNotBetween(String value1, String value2) {
            addCriterion("prevention not between", value1, value2, "prevention");
            return (Criteria) this;
        }

        public Criteria andDcaseIsNull() {
            addCriterion("dcase is null");
            return (Criteria) this;
        }

        public Criteria andDcaseIsNotNull() {
            addCriterion("dcase is not null");
            return (Criteria) this;
        }

        public Criteria andDcaseEqualTo(String value) {
            addCriterion("dcase =", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseNotEqualTo(String value) {
            addCriterion("dcase <>", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseGreaterThan(String value) {
            addCriterion("dcase >", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseGreaterThanOrEqualTo(String value) {
            addCriterion("dcase >=", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseLessThan(String value) {
            addCriterion("dcase <", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseLessThanOrEqualTo(String value) {
            addCriterion("dcase <=", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseLike(String value) {
            addCriterion("dcase like", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseNotLike(String value) {
            addCriterion("dcase not like", value, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseIn(List<String> values) {
            addCriterion("dcase in", values, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseNotIn(List<String> values) {
            addCriterion("dcase not in", values, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseBetween(String value1, String value2) {
            addCriterion("dcase between", value1, value2, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcaseNotBetween(String value1, String value2) {
            addCriterion("dcase not between", value1, value2, "dcase");
            return (Criteria) this;
        }

        public Criteria andDcheckIsNull() {
            addCriterion("dcheck is null");
            return (Criteria) this;
        }

        public Criteria andDcheckIsNotNull() {
            addCriterion("dcheck is not null");
            return (Criteria) this;
        }

        public Criteria andDcheckEqualTo(String value) {
            addCriterion("dcheck =", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckNotEqualTo(String value) {
            addCriterion("dcheck <>", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckGreaterThan(String value) {
            addCriterion("dcheck >", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckGreaterThanOrEqualTo(String value) {
            addCriterion("dcheck >=", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckLessThan(String value) {
            addCriterion("dcheck <", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckLessThanOrEqualTo(String value) {
            addCriterion("dcheck <=", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckLike(String value) {
            addCriterion("dcheck like", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckNotLike(String value) {
            addCriterion("dcheck not like", value, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckIn(List<String> values) {
            addCriterion("dcheck in", values, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckNotIn(List<String> values) {
            addCriterion("dcheck not in", values, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckBetween(String value1, String value2) {
            addCriterion("dcheck between", value1, value2, "dcheck");
            return (Criteria) this;
        }

        public Criteria andDcheckNotBetween(String value1, String value2) {
            addCriterion("dcheck not between", value1, value2, "dcheck");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
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