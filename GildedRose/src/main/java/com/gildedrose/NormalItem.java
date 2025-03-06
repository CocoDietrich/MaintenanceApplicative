package com.gildedrose;

class NormalItem extends UpdatableItem {
    public NormalItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(item.sellIn <= 0 ? 2 : 1);
        decreaseSellIn();
    }
}