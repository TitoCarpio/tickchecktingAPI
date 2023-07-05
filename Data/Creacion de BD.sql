--V2
drop TABLE public.userxpermision;
drop TABLE public.permision;
drop TABLE public.user;
drop TABLE public.eventxartist;
drop TABLE public.attend;
drop TABLE public.purchase;
drop TABLE public.transfer;
drop TABLE public.artist;
drop TABLE public.ticket;
drop TABLE public.event;
drop TABLE public.place;
drop TABLE public.event_category;
drop TABLE public.ticket_category;
drop TABLE public.eventxsponsor;
drop TABLE public.sponsor;

CREATE TABLE public.permision(
    name VARCHAR NOT NULL,
    created_date DATE NOT NULL,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
    CONSTRAINT permision_pk PRIMARY KEY (code),
    CONSTRAINT permision_name_un UNIQUE (name)
);


CREATE TABLE public.user(
    username VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    active boolean NOT NULL DEFAULT true,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
    CONSTRAINT user_pk PRIMARY KEY (code),
    CONSTRAINT user_email_un UNIQUE (email),
    CONSTRAINT user_username_un UNIQUE (username)
);

CREATE TABLE public.userxpermision(
    user_id uuid NOT NULL,
    permision_id uuid NOT NULL,
    created_date DATE NOT NULL,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
    CONSTRAINT userxpermision_pk PRIMARY KEY (code)
);

CREATE TABLE public.artist(
    name VARCHAR NOT NULL,
    created_date DATE NOT NULL,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
    CONSTRAINT artist_pk PRIMARY KEY (code),
    CONSTRAINT artist_name_un UNIQUE (name)
);

CREATE TABLE public.ticket (
	category_id uuid NOT NULL,
	created_date DATE NOT NULL,
    active boolean,
    purchase_id uuid NOT NULL,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT ticket_pk PRIMARY KEY (code)
);

CREATE TABLE public.ticket_category (
	name VARCHAR NOT NULL,
    price float4 NOT NULL,
    qty int NOT NULL,
    created_date DATE NOT NULL,
    upddat timestamp NOT NULL,
	event_id uuid NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT tkt_category_pk PRIMARY KEY (code)
);

CREATE TABLE public.event (
	name VARCHAR NOT NULL,
    image VARCHAR NOT NULL,
    category_id uuid NOT NULL,
    place_id uuid NOT NULL,
    event_date date NOT NULL,
    event_hour time NOT NULL,
    created_date DATE NOT NULL,
    upddat timestamp NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT event_pk PRIMARY KEY (code)
);

CREATE TABLE public.place (
	name VARCHAR NOT NULL,
    created_date DATE NOT NULL,
    upddat timestamp NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT place_pk PRIMARY KEY (code)
);

CREATE TABLE public.event_category (
	name VARCHAR NOT NULL,
    created_date DATE NOT NULL,
    upddat timestamp NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT event_cty_pk PRIMARY KEY (code)
);

CREATE TABLE public.eventxartist (
	artist_id uuid NOT NULL,
	event_id uuid NOT NULL,
	created_date DATE NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT eventxartist_pk PRIMARY KEY (code)
);

CREATE TABLE public.eventxsponsor (
	sponsor_id uuid NOT NULL,
	event_id uuid NOT NULL,
	created_date DATE NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT eventxsponsor_pk PRIMARY KEY (code)
);

CREATE TABLE public.sponsor (
	name VARCHAR NOT NULL,
    created_date DATE NOT NULL,
    code uuid NOT NULL DEFAULT gen_random_uuid(),
    CONSTRAINT sponsor_pk PRIMARY KEY (code),
    CONSTRAINT sponsor_name_un UNIQUE (name)
);

CREATE TABLE public.attend (
	user_id uuid NOT NULL,
	event_id uuid NOT NULL,
    actdat timestamp NOT NULL,
    upddat timestamp NOT NULL,
    ticket_id uuid NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT attend_pk PRIMARY KEY (code)
);


CREATE TABLE public.purchase (
	user_id uuid NOT NULL,
	actdat DATE NOT NULL,
    upddat DATE NOT NULL,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT purchase_pk PRIMARY KEY (code)
);


CREATE TABLE public.transfer (
	sender_user_id uuid NOT NULL,
    reciver_user_id uuid NOT NULL,
    ticket_id uuid not null,
    created_date date,
    hastemp varchar,
    actdat date,
    status boolean,
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	CONSTRAINT transfer_pk PRIMARY KEY (code)
);

CREATE TABLE public."token" (
	code uuid NOT NULL DEFAULT gen_random_uuid(),
	"content" varchar NOT NULL,
	active boolean NOT NULL DEFAULT true,
	"timestamp" timestamp without time zone NULL DEFAULT CURRENT_TIMESTAMP,
	user_id uuid NULL,
	CONSTRAINT token_pk PRIMARY KEY (code),
	CONSTRAINT token_fk FOREIGN KEY (user_id) REFERENCES public."user"(code) ON DELETE CASCADE ON UPDATE CASCADE
);



--user connection
ALTER TABLE public.userxpermision ADD CONSTRAINT userxpermi_fk FOREIGN KEY (user_id) REFERENCES public.user(code);
ALTER TABLE public.attend ADD CONSTRAINT userattend_fk FOREIGN KEY (user_id) REFERENCES public.user(code);  
ALTER TABLE public.purchase ADD CONSTRAINT userpurchase_fk FOREIGN KEY (user_id) REFERENCES public.user(code);  
ALTER TABLE public.transfer ADD CONSTRAINT userSendertransfer_fk FOREIGN KEY (sender_user_id) REFERENCES public.user(code);  
ALTER TABLE public.transfer ADD CONSTRAINT userRecivertransfer_fk FOREIGN KEY (reciver_user_id) REFERENCES public.user(code);  

--permision connection
ALTER TABLE public.userxpermision ADD CONSTRAINT permi_fk FOREIGN KEY (permision_id) REFERENCES public.permision(code);

--ticket category connection
ALTER TABLE public.ticket ADD CONSTRAINT tktcty_fk FOREIGN KEY (category_id) REFERENCES public.ticket_category(code);
ALTER TABLE public.ticket ADD CONSTRAINT tktprchs_fk FOREIGN KEY (purchase_id) REFERENCES public.purchase(code);

--artist connection
ALTER TABLE public.eventxartist ADD CONSTRAINT arts_event_fk FOREIGN KEY (artist_id) REFERENCES public.artist(code);

--event connections
ALTER TABLE public.eventxsponsor ADD CONSTRAINT eventSponsor_fk FOREIGN KEY (event_id) REFERENCES public.event(code);
ALTER TABLE public.eventxartist ADD CONSTRAINT eventRegis_fk FOREIGN KEY (event_id) REFERENCES public.event(code);
ALTER TABLE public.ticket_category ADD CONSTRAINT tktcty_event_fk FOREIGN KEY (event_id) REFERENCES public.event(code);
ALTER TABLE public.attend ADD CONSTRAINT event_attend_fk FOREIGN KEY (event_id) REFERENCES public.event(code);

--ticket connections
ALTER TABLE public.transfer ADD CONSTRAINT TKTtransfer_fk FOREIGN KEY (ticket_id) REFERENCES public.ticket(code);  
ALTER TABLE public.attend ADD CONSTRAINT TKTattend_fk FOREIGN KEY (ticket_id) REFERENCES public.ticket(code); 

--place connections
ALTER TABLE public.event ADD CONSTRAINT place_event_fk FOREIGN KEY (place_id) REFERENCES public.place(code);  

--sponsor connections
ALTER TABLE public.eventxsponsor ADD CONSTRAINT sponsor_event_fk FOREIGN KEY (sponsor_id) REFERENCES public.sponsor(code);  

--event_category connections
ALTER TABLE public.event ADD CONSTRAINT event_category_fk FOREIGN KEY (category_id) REFERENCES public.event_category(code);  


--relaciones OneToOne
--Attend-ticket
--transfer-ticket

--las demas son ManyToOne
