/*
 Navicat Premium Data Transfer

 Source Server         : local-db
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : localhost:5432
 Source Catalog        : oauth-db
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 28/11/2022 22:44:45
*/


-- ----------------------------
-- Table structure for client_credential
-- ----------------------------
DROP TABLE IF EXISTS "public"."client_credential";
CREATE TABLE "public"."client_credential" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "client_id" varchar(50) COLLATE "pg_catalog"."default",
  "client_secret" varchar(50) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."client_credential" OWNER TO "postgres";

-- ----------------------------
-- Records of client_credential
-- ----------------------------
BEGIN;
INSERT INTO "public"."client_credential" VALUES ('5cbaab8c-6f20-11ed-a1eb-0242ac120002', 'id-2', 'secret-2');
INSERT INTO "public"."client_credential" VALUES ('26fc11b6-6f20-11ed-a1eb-0242ac120002', 'id-1', 'secret-1');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "username" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."users" OWNER TO "postgres";

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO "public"."users" VALUES ('5cbaab8c-6f20-11ed-a1eb-0242ac120005', 'reza', 'reza');
INSERT INTO "public"."users" VALUES ('5cbaab8c-6f20-11ed-a1eb-0242ac12111', 'septian', 'septian');
COMMIT;
