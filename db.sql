-- DDL --

DROP TABLE IF EXISTS products;

CREATE TABLE public.products (
  article INT NOT NULL PRIMARY KEY,
  manufacturer VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  weight NUMERIC NOT NULL,
  price NUMERIC NOT NULL
);


-- DML --

INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (11168, 'Royal Canin', 'Buldog 12', 12, 3830);
INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (11167, 'Royal Canin', 'Buldog 12', 3, 1163);
INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (11528, 'Purina', 'Pro Plan', 14, 4774);
INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (19681, 'Purina', 'Pro Plan', 7, 2502);
INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (19633, 'Purina', 'Pro Plan', 3, 1153);
INSERT INTO public.products (article, manufacturer, name, weight, price) VALUES (19631, 'Purina', 'Pro Plan', 1.5, 634);

