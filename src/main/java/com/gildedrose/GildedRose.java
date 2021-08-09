package com.gildedrose;

class GildedRose {
    private final Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final Integer BACKSTAGE_PASSES_DOUBLE_INCREASE_QUALITY_SELL_IN_THRESHOLD = 10;
    private static final Integer BACKSTAGE_PASSES_TRIPLE_INCREASE_QUALITY_SELL_IN_THRESHOLD = 5;
    private static final Integer BACKSTAGE_PASSES_RESET_QUALITY_SELL_IN_THRESHOLD = 0;
    private static final Integer DEFAULT_ITEM_DOUBLE_DECREASE_QUALITY_SELL_IN_THRESHOLD = 0;
    private static final Integer AGED_BRIE_DOUBLE_DECREASE_QUALITY_SELL_IN_THRESHOLD = 0;

    private static final Integer MAX_QUALITY = 50;
    private static final Integer MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case AGED_BRIE:
                    updateQualityAgedBrie(item);
                    decreaseSellIn(item);
                    break;
                case BACKSTAGE:
                    updateQualityBackstagePasses(item);
                    decreaseSellIn(item);
                    break;
                case SULFURAS:
                    break;
                default:
                    updateQualityDefaultItem(item);
                    decreaseSellIn(item);
                    break;
            }
        }

    }

    private void updateQualityAgedBrie(Item item) {
        increaseQuality(item);

        if (item.sellIn < AGED_BRIE_DOUBLE_DECREASE_QUALITY_SELL_IN_THRESHOLD) {
            increaseQuality(item);
        }
    }

    private void updateQualityDefaultItem(Item item) {
        decreaseQuality(item);

        if (item.sellIn <= DEFAULT_ITEM_DOUBLE_DECREASE_QUALITY_SELL_IN_THRESHOLD) {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality -= 1;
        }
    }

    private void resetQuality(Item item) {
        item.quality = MIN_QUALITY;
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality += 1;
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateQualityBackstagePasses(Item item) {
        increaseQuality(item);

        if (item.sellIn <= BACKSTAGE_PASSES_DOUBLE_INCREASE_QUALITY_SELL_IN_THRESHOLD) {
            increaseQuality(item);
        }

        if (item.sellIn <= BACKSTAGE_PASSES_TRIPLE_INCREASE_QUALITY_SELL_IN_THRESHOLD) {
            increaseQuality(item);
        }

        if (item.sellIn <= BACKSTAGE_PASSES_RESET_QUALITY_SELL_IN_THRESHOLD) {
            resetQuality(item);
        }
    }

}
