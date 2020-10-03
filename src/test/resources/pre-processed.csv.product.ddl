create table TB_PRODUCT
(

    productId          serial not null,

    productCode        varchar(30),

    productName        varchar(100),

    marketCap          numeric(24, 10),

    avgVolume          numeric(24, 10),

    peRatio            numeric(24, 10),

    revenue            numeric(24, 10),

    totalCash          numeric(24, 10),

    totalDevidendYield numeric(24, 10),

    avgDividendYield   numeric(24, 10),

    sector             varchar(30),

    industry           varchar(30),

    price              numeric(24, 10),

    priceOneDayChange  numeric(24, 10),

    creationDateTime   timestamp,

    updateDateTime     timestamp
)