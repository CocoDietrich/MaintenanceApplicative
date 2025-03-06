package com.gildedrose;

class ConjuredItem extends UpdatableItem {
    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(item.sellIn <= 0 ? 4 : 2);
        decreaseSellIn();
    }
}