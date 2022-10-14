PGDMP                     	    z         	   Newspaper    14.5    14.5 $               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16394 	   Newspaper    DATABASE     o   CREATE DATABASE "Newspaper" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE "Newspaper";
                postgres    false            �            1259    16490    bill    TABLE     �   CREATE TABLE public.bill (
    bill_id integer NOT NULL,
    bill_date date NOT NULL,
    bill_month text NOT NULL,
    cust_id integer NOT NULL
);
    DROP TABLE public.bill;
       public         heap    postgres    false            �            1259    16489    bill_bill_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bill_bill_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.bill_bill_id_seq;
       public          postgres    false    214                       0    0    bill_bill_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.bill_bill_id_seq OWNED BY public.bill.bill_id;
          public          postgres    false    213            �            1259    16522    bill_particular    TABLE     �   CREATE TABLE public.bill_particular (
    bpart_id integer NOT NULL,
    bill_id integer NOT NULL,
    part_id integer NOT NULL
);
 #   DROP TABLE public.bill_particular;
       public         heap    postgres    false            �            1259    16521    bill_particular_bpart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bill_particular_bpart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.bill_particular_bpart_id_seq;
       public          postgres    false    216                       0    0    bill_particular_bpart_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.bill_particular_bpart_id_seq OWNED BY public.bill_particular.bpart_id;
          public          postgres    false    215            �            1259    16406    customer    TABLE     �   CREATE TABLE public.customer (
    cust_id integer NOT NULL,
    cust_name text NOT NULL,
    cust_address1 text NOT NULL,
    cust_address2 text NOT NULL,
    cust_phone integer NOT NULL
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    16405    customer_cust_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_cust_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.customer_cust_id_seq;
       public          postgres    false    210                       0    0    customer_cust_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.customer_cust_id_seq OWNED BY public.customer.cust_id;
          public          postgres    false    209            �            1259    16424 
   particular    TABLE     �   CREATE TABLE public.particular (
    part_id integer NOT NULL,
    part_name text NOT NULL,
    part_amount numeric NOT NULL
);
    DROP TABLE public.particular;
       public         heap    postgres    false            �            1259    16423    particular_part_id_seq    SEQUENCE     �   CREATE SEQUENCE public.particular_part_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.particular_part_id_seq;
       public          postgres    false    212                       0    0    particular_part_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.particular_part_id_seq OWNED BY public.particular.part_id;
          public          postgres    false    211            m           2604    16493    bill bill_id    DEFAULT     l   ALTER TABLE ONLY public.bill ALTER COLUMN bill_id SET DEFAULT nextval('public.bill_bill_id_seq'::regclass);
 ;   ALTER TABLE public.bill ALTER COLUMN bill_id DROP DEFAULT;
       public          postgres    false    214    213    214            n           2604    16525    bill_particular bpart_id    DEFAULT     �   ALTER TABLE ONLY public.bill_particular ALTER COLUMN bpart_id SET DEFAULT nextval('public.bill_particular_bpart_id_seq'::regclass);
 G   ALTER TABLE public.bill_particular ALTER COLUMN bpart_id DROP DEFAULT;
       public          postgres    false    215    216    216            k           2604    16409    customer cust_id    DEFAULT     t   ALTER TABLE ONLY public.customer ALTER COLUMN cust_id SET DEFAULT nextval('public.customer_cust_id_seq'::regclass);
 ?   ALTER TABLE public.customer ALTER COLUMN cust_id DROP DEFAULT;
       public          postgres    false    209    210    210            l           2604    16427    particular part_id    DEFAULT     x   ALTER TABLE ONLY public.particular ALTER COLUMN part_id SET DEFAULT nextval('public.particular_part_id_seq'::regclass);
 A   ALTER TABLE public.particular ALTER COLUMN part_id DROP DEFAULT;
       public          postgres    false    211    212    212                      0    16490    bill 
   TABLE DATA           G   COPY public.bill (bill_id, bill_date, bill_month, cust_id) FROM stdin;
    public          postgres    false    214   �(                 0    16522    bill_particular 
   TABLE DATA           E   COPY public.bill_particular (bpart_id, bill_id, part_id) FROM stdin;
    public          postgres    false    216   )                 0    16406    customer 
   TABLE DATA           `   COPY public.customer (cust_id, cust_name, cust_address1, cust_address2, cust_phone) FROM stdin;
    public          postgres    false    210   ))       
          0    16424 
   particular 
   TABLE DATA           E   COPY public.particular (part_id, part_name, part_amount) FROM stdin;
    public          postgres    false    212   F)                  0    0    bill_bill_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.bill_bill_id_seq', 1, false);
          public          postgres    false    213                       0    0    bill_particular_bpart_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.bill_particular_bpart_id_seq', 1, false);
          public          postgres    false    215                       0    0    customer_cust_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.customer_cust_id_seq', 1, false);
          public          postgres    false    209                       0    0    particular_part_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.particular_part_id_seq', 1, false);
          public          postgres    false    211            x           2606    16527 $   bill_particular bill_particular_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.bill_particular
    ADD CONSTRAINT bill_particular_pkey PRIMARY KEY (bpart_id);
 N   ALTER TABLE ONLY public.bill_particular DROP CONSTRAINT bill_particular_pkey;
       public            postgres    false    216            v           2606    16497    bill bill_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (bill_id);
 8   ALTER TABLE ONLY public.bill DROP CONSTRAINT bill_pkey;
       public            postgres    false    214            p           2606    16415     customer customer_cust_phone_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_cust_phone_key UNIQUE (cust_phone);
 J   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_cust_phone_key;
       public            postgres    false    210            r           2606    16413    customer customer_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (cust_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    210            t           2606    16431    particular particular_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.particular
    ADD CONSTRAINT particular_pkey PRIMARY KEY (part_id);
 D   ALTER TABLE ONLY public.particular DROP CONSTRAINT particular_pkey;
       public            postgres    false    212            y           2606    16498    bill bill_cust_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_cust_id_fkey FOREIGN KEY (cust_id) REFERENCES public.customer(cust_id) ON UPDATE CASCADE ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.bill DROP CONSTRAINT bill_cust_id_fkey;
       public          postgres    false    3186    210    214            z           2606    16528 ,   bill_particular bill_particular_bill_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bill_particular
    ADD CONSTRAINT bill_particular_bill_id_fkey FOREIGN KEY (bill_id) REFERENCES public.bill(bill_id) ON UPDATE CASCADE ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.bill_particular DROP CONSTRAINT bill_particular_bill_id_fkey;
       public          postgres    false    3190    214    216            {           2606    16533 ,   bill_particular bill_particular_part_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bill_particular
    ADD CONSTRAINT bill_particular_part_id_fkey FOREIGN KEY (part_id) REFERENCES public.particular(part_id) ON UPDATE CASCADE ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.bill_particular DROP CONSTRAINT bill_particular_part_id_fkey;
       public          postgres    false    212    216    3188                  x������ � �            x������ � �            x������ � �      
      x������ � �     