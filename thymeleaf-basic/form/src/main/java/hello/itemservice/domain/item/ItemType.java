package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) { //설명을 위한 description 필드 추가
        this.description = description;
    }
}
