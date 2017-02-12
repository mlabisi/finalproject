package edu.cpp.cs.cs141.final_project.Mora;

/**
 * This enum type will represent the specific type of items that
 * exist in the spy game.
 *
 * @author Mora Labisi
 */
public enum ItemType {
    RADAR("\\uD83D\\uDCE1"),
    INVULNERABILITY("\\uD83D\\uDEE1"),
    EXTRA_BULLET("\\uD83D\\uDD2B"),
    BRIEFCASE("\\uD83D\\uDCBC");

    final private String ICON;

    /**
     * @param icon The icon to be assigned
     */
    ItemType(String icon){
        this.ICON = icon;
    }

    /**
     * @return The {@code String} representation of the item.
     */
    @Override
    public String toString(){
        return ICON;
    }
}
