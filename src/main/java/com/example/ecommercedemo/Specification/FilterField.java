//package com.example.ecommercedemo.Specification;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Setter
//@Getter
//@NoArgsConstructor
//public class FilterField {
//
//    private long id;
//    private String name;
//    private int page;
//    private int pageSize;
//    private int minPrice;
//    private int maxPrice;
//    private String phone;
//    private String email;
//
//
//    public static final class FilterFieldBuilder {
//        private long id;
//        private String name;
//        private int page;
//        private int pageSize;
//        private int minPrice;
//        private int maxPrice;
//        private String phone;
//        private String email;
//
//        private FilterFieldBuilder() {
//        }
//
//        public static FilterFieldBuilder aFilterField() {
//            return new FilterFieldBuilder();
//        }
//
//        public FilterFieldBuilder withId(long id) {
//            this.id = id;
//            return this;
//        }
//
//        public FilterFieldBuilder withName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public FilterFieldBuilder withPage(int page) {
//            this.page = page;
//            return this;
//        }
//
//        public FilterFieldBuilder withPageSize(int pageSize) {
//            this.pageSize = pageSize;
//            return this;
//        }
//
//        public FilterFieldBuilder withMinPrice(int minPrice) {
//            this.minPrice = minPrice;
//            return this;
//        }
//
//        public FilterFieldBuilder withMaxPrice(int maxPrice) {
//            this.maxPrice = maxPrice;
//            return this;
//        }
//
//        public FilterFieldBuilder withPhone(String phone) {
//            this.phone = phone;
//            return this;
//        }
//
//        public FilterFieldBuilder withEmail(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public FilterField build() {
//            FilterField filterField = new FilterField();
//            filterField.setId(id);
//            filterField.setName(name);
//            filterField.setPage(page);
//            filterField.setPageSize(pageSize);
//            filterField.setMinPrice(minPrice);
//            filterField.setMaxPrice(maxPrice);
//            filterField.setPhone(phone);
//            filterField.setEmail(email);
//            return filterField;
//        }
//    }
//}
