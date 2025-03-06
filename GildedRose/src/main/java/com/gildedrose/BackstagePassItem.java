package com.gildedrose;

class BackstagePassItem extends UpdatableItem {
    public BackstagePassItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.sellIn > 10) {
            increaseQuality(1);
        } else if (item.sellIn > 5) {
            increaseQuality(2);
        } else if (item.sellIn > 0) {
            increaseQuality(3);
        } else {
            item.quality = 0;
        }
        decreaseSellIn();
    }
}