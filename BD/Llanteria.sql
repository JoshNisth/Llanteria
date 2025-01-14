PGDMP     $                    |         	   Llanteria    15.3    15.3 :    E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            H           1262    33427 	   Llanteria    DATABASE     �   CREATE DATABASE "Llanteria" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Bolivia.1252';
    DROP DATABASE "Llanteria";
                postgres    false            �            1259    33428    cliente    TABLE     5  CREATE TABLE public.cliente (
    carnet_cliente integer NOT NULL,
    nombre_cliente character varying(40) NOT NULL,
    direccion character varying(40) NOT NULL,
    tipo_cliente character varying(40) NOT NULL,
    correo_cliente character varying(40) NOT NULL,
    celular_cliente character varying(40)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    33431    cliente_seq    SEQUENCE     t   CREATE SEQUENCE public.cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.cliente_seq;
       public          postgres    false            �            1259    33432    factura    TABLE     �   CREATE TABLE public.factura (
    factura_id integer NOT NULL,
    venta_id integer,
    neumatico_id integer,
    cantidad integer,
    precio_unitario numeric
);
    DROP TABLE public.factura;
       public         heap    postgres    false            �            1259    33437     detalleventa_detalleventa_id_seq    SEQUENCE     �   CREATE SEQUENCE public.detalleventa_detalleventa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.detalleventa_detalleventa_id_seq;
       public          postgres    false    216            I           0    0     detalleventa_detalleventa_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.detalleventa_detalleventa_id_seq OWNED BY public.factura.factura_id;
          public          postgres    false    217            �            1259    33438    entrega    TABLE     �   CREATE TABLE public.entrega (
    entrega_id integer NOT NULL,
    fecha_entrega date NOT NULL,
    cantidad integer NOT NULL,
    productos character varying(50) NOT NULL,
    estado boolean NOT NULL,
    pedido_id integer NOT NULL
);
    DROP TABLE public.entrega;
       public         heap    postgres    false            �            1259    33441    entrega_seq    SEQUENCE     t   CREATE SEQUENCE public.entrega_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.entrega_seq;
       public          postgres    false            �            1259    33442    factura_seq    SEQUENCE     t   CREATE SEQUENCE public.factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.factura_seq;
       public          postgres    false            �            1259    33443    funcionario    TABLE     D  CREATE TABLE public.funcionario (
    carnet_fun integer NOT NULL,
    nombre_funcionario character varying(40) NOT NULL,
    sueldo numeric NOT NULL,
    fecha_contratacion date NOT NULL,
    numero_celular integer NOT NULL,
    correo_funcionario character varying(40) NOT NULL,
    "contraseña" character varying(40)
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    33448    funcionario_seq    SEQUENCE     x   CREATE SEQUENCE public.funcionario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.funcionario_seq;
       public          postgres    false            �            1259    33449 	   neumatico    TABLE        CREATE TABLE public.neumatico (
    neumatico_id integer NOT NULL,
    "tamaño" numeric NOT NULL,
    modelo character varying(50) NOT NULL,
    estado boolean NOT NULL,
    proveedor_id integer NOT NULL,
    cantidad_stock integer,
    precio numeric
);
    DROP TABLE public.neumatico;
       public         heap    postgres    false            �            1259    33454    neumatico_seq    SEQUENCE     v   CREATE SEQUENCE public.neumatico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.neumatico_seq;
       public          postgres    false            �            1259    33455    pago_seq    SEQUENCE     q   CREATE SEQUENCE public.pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.pago_seq;
       public          postgres    false            �            1259    33456    pedido    TABLE     �   CREATE TABLE public.pedido (
    pedido_id integer NOT NULL,
    cantidad_pedida integer NOT NULL,
    producto_pedido character varying(50) NOT NULL,
    carnet_fun integer NOT NULL,
    proveedor_id integer,
    fecha_pedido date
);
    DROP TABLE public.pedido;
       public         heap    postgres    false            �            1259    33459 
   pedido_seq    SEQUENCE     s   CREATE SEQUENCE public.pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.pedido_seq;
       public          postgres    false            �            1259    33460 	   proveedor    TABLE     �   CREATE TABLE public.proveedor (
    proveedor_id integer NOT NULL,
    nombre_empresa character varying(50) NOT NULL,
    direccion_empresa character varying(50) NOT NULL,
    telefono integer NOT NULL,
    historial character varying(50) NOT NULL
);
    DROP TABLE public.proveedor;
       public         heap    postgres    false            �            1259    33463    proveedor_seq    SEQUENCE     v   CREATE SEQUENCE public.proveedor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.proveedor_seq;
       public          postgres    false            �            1259    33464    venta    TABLE     �   CREATE TABLE public.venta (
    venta_id integer NOT NULL,
    fecha_venta date NOT NULL,
    monto_total numeric NOT NULL,
    carnet_fun integer NOT NULL,
    carnet_cliente integer NOT NULL
);
    DROP TABLE public.venta;
       public         heap    postgres    false            �            1259    33469 	   venta_seq    SEQUENCE     r   CREATE SEQUENCE public.venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.venta_seq;
       public          postgres    false            �           2604    33470    factura factura_id    DEFAULT     �   ALTER TABLE ONLY public.factura ALTER COLUMN factura_id SET DEFAULT nextval('public.detalleventa_detalleventa_id_seq'::regclass);
 A   ALTER TABLE public.factura ALTER COLUMN factura_id DROP DEFAULT;
       public          postgres    false    217    216            1          0    33428    cliente 
   TABLE DATA           {   COPY public.cliente (carnet_cliente, nombre_cliente, direccion, tipo_cliente, correo_cliente, celular_cliente) FROM stdin;
    public          postgres    false    214   TC       5          0    33438    entrega 
   TABLE DATA           d   COPY public.entrega (entrega_id, fecha_entrega, cantidad, productos, estado, pedido_id) FROM stdin;
    public          postgres    false    218   bD       3          0    33432    factura 
   TABLE DATA           `   COPY public.factura (factura_id, venta_id, neumatico_id, cantidad, precio_unitario) FROM stdin;
    public          postgres    false    216   �D       8          0    33443    funcionario 
   TABLE DATA           �   COPY public.funcionario (carnet_fun, nombre_funcionario, sueldo, fecha_contratacion, numero_celular, correo_funcionario, "contraseña") FROM stdin;
    public          postgres    false    221   {E       :          0    33449 	   neumatico 
   TABLE DATA           r   COPY public.neumatico (neumatico_id, "tamaño", modelo, estado, proveedor_id, cantidad_stock, precio) FROM stdin;
    public          postgres    false    223   F       =          0    33456    pedido 
   TABLE DATA           u   COPY public.pedido (pedido_id, cantidad_pedida, producto_pedido, carnet_fun, proveedor_id, fecha_pedido) FROM stdin;
    public          postgres    false    226   4G       ?          0    33460 	   proveedor 
   TABLE DATA           i   COPY public.proveedor (proveedor_id, nombre_empresa, direccion_empresa, telefono, historial) FROM stdin;
    public          postgres    false    228   �G       A          0    33464    venta 
   TABLE DATA           _   COPY public.venta (venta_id, fecha_venta, monto_total, carnet_fun, carnet_cliente) FROM stdin;
    public          postgres    false    230   �H       J           0    0    cliente_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.cliente_seq', 1, false);
          public          postgres    false    215            K           0    0     detalleventa_detalleventa_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.detalleventa_detalleventa_id_seq', 4, true);
          public          postgres    false    217            L           0    0    entrega_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.entrega_seq', 3, true);
          public          postgres    false    219            M           0    0    factura_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.factura_seq', 1, false);
          public          postgres    false    220            N           0    0    funcionario_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.funcionario_seq', 2, true);
          public          postgres    false    222            O           0    0    neumatico_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.neumatico_seq', 3, true);
          public          postgres    false    224            P           0    0    pago_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('public.pago_seq', 1, false);
          public          postgres    false    225            Q           0    0 
   pedido_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.pedido_seq', 3, true);
          public          postgres    false    227            R           0    0    proveedor_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.proveedor_seq', 3, true);
          public          postgres    false    229            S           0    0 	   venta_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.venta_seq', 1, false);
          public          postgres    false    231            �           2606    33472    cliente cliente_pk 
   CONSTRAINT     \   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pk PRIMARY KEY (carnet_cliente);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pk;
       public            postgres    false    214            �           2606    33474    factura detalleventa_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT detalleventa_pkey PRIMARY KEY (factura_id);
 C   ALTER TABLE ONLY public.factura DROP CONSTRAINT detalleventa_pkey;
       public            postgres    false    216            �           2606    33476    entrega entrega_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.entrega
    ADD CONSTRAINT entrega_pk PRIMARY KEY (entrega_id);
 <   ALTER TABLE ONLY public.entrega DROP CONSTRAINT entrega_pk;
       public            postgres    false    218            �           2606    33478    funcionario funcionario_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (carnet_fun);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pk;
       public            postgres    false    221            �           2606    33480    neumatico neumatico_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.neumatico
    ADD CONSTRAINT neumatico_pk PRIMARY KEY (neumatico_id);
 @   ALTER TABLE ONLY public.neumatico DROP CONSTRAINT neumatico_pk;
       public            postgres    false    223            �           2606    33482    pedido pedido_pk 
   CONSTRAINT     U   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (pedido_id);
 :   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pk;
       public            postgres    false    226            �           2606    33484    proveedor proveedor_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proveedor
    ADD CONSTRAINT proveedor_pk PRIMARY KEY (proveedor_id);
 @   ALTER TABLE ONLY public.proveedor DROP CONSTRAINT proveedor_pk;
       public            postgres    false    228            �           2606    33486    venta venta_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT venta_pk PRIMARY KEY (venta_id);
 8   ALTER TABLE ONLY public.venta DROP CONSTRAINT venta_pk;
       public            postgres    false    230            �           2606    33487 !   factura detalleventa_neumatico_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT detalleventa_neumatico_fk FOREIGN KEY (neumatico_id) REFERENCES public.neumatico(neumatico_id);
 K   ALTER TABLE ONLY public.factura DROP CONSTRAINT detalleventa_neumatico_fk;
       public          postgres    false    3220    216    223            �           2606    33492    factura detalleventa_venta_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT detalleventa_venta_fk FOREIGN KEY (venta_id) REFERENCES public.venta(venta_id);
 G   ALTER TABLE ONLY public.factura DROP CONSTRAINT detalleventa_venta_fk;
       public          postgres    false    230    3226    216            �           2606    33497    entrega entrega_pedido    FK CONSTRAINT        ALTER TABLE ONLY public.entrega
    ADD CONSTRAINT entrega_pedido FOREIGN KEY (pedido_id) REFERENCES public.pedido(pedido_id);
 @   ALTER TABLE ONLY public.entrega DROP CONSTRAINT entrega_pedido;
       public          postgres    false    218    3222    226            �           2606    33502    venta envio_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT envio_funcionario FOREIGN KEY (carnet_fun) REFERENCES public.funcionario(carnet_fun);
 A   ALTER TABLE ONLY public.venta DROP CONSTRAINT envio_funcionario;
       public          postgres    false    221    230    3218            �           2606    33507    pedido fk_proveedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk_proveedor FOREIGN KEY (proveedor_id) REFERENCES public.proveedor(proveedor_id);
 =   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fk_proveedor;
       public          postgres    false    3224    226    228            �           2606    33512    neumatico neumatico_proveedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.neumatico
    ADD CONSTRAINT neumatico_proveedor FOREIGN KEY (proveedor_id) REFERENCES public.proveedor(proveedor_id);
 G   ALTER TABLE ONLY public.neumatico DROP CONSTRAINT neumatico_proveedor;
       public          postgres    false    3224    223    228            �           2606    33517    pedido pedido_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_funcionario FOREIGN KEY (carnet_fun) REFERENCES public.funcionario(carnet_fun);
 C   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_funcionario;
       public          postgres    false    221    3218    226            �           2606    33522    venta venta_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT venta_cliente FOREIGN KEY (carnet_cliente) REFERENCES public.cliente(carnet_cliente);
 =   ALTER TABLE ONLY public.venta DROP CONSTRAINT venta_cliente;
       public          postgres    false    3212    214    230            1   �   x�U��j1��'O�'�l~L�NiA�ZĂW�b���HbZ����s3g�9s.$�
������[�F���p��R��Ї��,[�3��Z����9��<P���o��+���㼞 R��0���&����!&�'_;istɹb\�����`sq���[�%���s��܏y����j�\j�8z�n�}�ֶ/��&?�a6\k��1���K�G���ga���婘�km��̝������/!?��t      5   �   x�E˱� �����u�ib�dt!�$��~�l��aҝ6�Y�qG�0�C��HE��H3�Ы<k:��_�sN�Â�踻�>"�\Z�u���t����k]�K�-�a|�Q��jN-��
!��j+V      3   t   x�5��� C��0=PTܥ��Q����#0XV�}T%:Z�鰎��Q8�g|�LZ�0�8�U"�2Q6"u/��Q��Kh���XO��Z	^�T~'�[~�Ej�.T�GD>�S ,      8   z   x�M̱
�0����)�	�˵�������R%h�A��M'����'��1�2w��.�Q� x�5��8h�l�׺n�����Z2IО� 3�M�~��Rn�?B'��� ]���5�<3F#�      :     x�m��n�0�g�)n������0���]���9f�O_�3��N�����L�r.�[0�����Gt����<��+�K1�bJR~�}w�&��9[�w	��>�i�L��i���΂�u>M{Ak���:�$+�tO�tVo����"��\-�;X[;;a3z���2��ǣ�r�(��҉�&IB{D2�G8]}��O����5�WO�}oZ�.�0�>:!-�\PO?�������	i��f7أ�0@���QI���i��ڔܿ~���e]���k�9����?      =   �   x�M��� E��W��*b�cM;�dGR�JB����e�8�{�ebE�0�C��HEN����j�I+m:V�a����/��P�9Abۏ���#�ۑ�JYK�_3Ě9�\�}�Kqi��JxDL�ٱ;�՜���W`����'-y      ?   �   x�}��
�0 痯xP�M�G-"nus	ͣ�Ƽ���o\�ێ���y���q�'3s(�s�5wжm�JUC��gO�ٚ�~�B�4�$$죳#�́���IR6�^�[U�����8��a�MbQÑپ�D�M��S�Z5R���sr�!�e�C�      A   �   x�m�A!��74���e����d��d�@Rՠ7�(ޥ�	lf���=0fi= MH3R�:4n�źD;׶����%�Q�נT��a�'�4����1�Ţ`H��cW�ξ��. Y�QHxT����?� ;I     