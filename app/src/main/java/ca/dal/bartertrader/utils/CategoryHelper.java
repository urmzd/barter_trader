package ca.dal.bartertrader.utils;

public class CategoryHelper {
    public static String CategoryToString(CategoryEnum category)
    {
        switch (category)
        {
            case ELECTRONICS:
                return "Electronics";
            case CLOTHING:
                return "Clothing";
            case FURNITURE:
                return "Furniture";
            case BOOKS:
                return "Books";
            case SPORTING_GOODS:
                return "Sporting Goods";
            case TOOLS:
                return "Tools";
            case HOBBIES_AND_CRAFTS:
                return "Hobbies/Crafts";
            case MISC:
                return "Misc";
            default:
                return "Unknown Category";
        }
    }

    public static CategoryEnum StringToCategory(String categoryText)
    {
        switch(categoryText)
        {
            case "Electronics":
                return CategoryEnum.ELECTRONICS;
            case "Clothing":
                return CategoryEnum.CLOTHING;
            case "Furniture":
                return CategoryEnum.FURNITURE;
            case "Books":
                return CategoryEnum.BOOKS;
            case "Sporting Good":
                return CategoryEnum.SPORTING_GOODS;
            case "Tools":
                return CategoryEnum.TOOLS;
            case "Hobbies/Crafts":
                return CategoryEnum.HOBBIES_AND_CRAFTS;
            case "Misc":
                return CategoryEnum.MISC;
            default:
                return CategoryEnum.UNKNOWN_CATEGORY;
        }
    }
}