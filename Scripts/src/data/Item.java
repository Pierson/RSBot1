package data;

public enum Item {
    //Melee Weapons - Bronze, Iron, Steel, Black, Mithril, Adamant, Rune\\
    BATTLEAXE(new int[] {1375, 1363, 1365, 1367, 1369, 1371, 1373}),
    CLAW(new int[] {3095, 3096, 3097, 3098, 3099, 3100, 3101}),
    DAGGER(new int[] {1205, 1203, 1207, 1209, 1211, 1213}),
    HATCHET(new int[] {1351, 1349, 1353, 1361, 1355, 1357, 1359}),
    LONGSWORD(new int[] {1291, 1293, 1295, 1297, 1299, 1301, 1303}),
    MACE(new int[] {1422, 1420, 1424, 1426, 1428, 1430, 1432}),
    PICKAXE(new int[] {1265, 1267, 1269, 1273, 1271, 1275}),
    SCIMITAR(new int[] {1321, 1323, 1325, 1327, 1329, 1331, 1333}),
    SPEAR(new int[] {1237, 1239, 1241, 4580, 1243, 1245, 1247}),
    SWORD(new int[] {1277, 1279, 1281, 1283, 1285, 1287, 1289}),
    TWOHAND_SWORD(new int[] {1307, 1309, 1311, 1313, 1315, 1317, 1319}),
    WARHAMMER(new int[] {1337, 1335, 1339, 1341, 1343, 1345, 1347}),
    
    //Range Weapons\\
    CROSSBOW(new int[] {9174, 9177, 9179, 9181, 9183, 9185}),
    DART(new int[] {819, 820, 821, 822, 823, 824}),
    JAVELIN(new int[] {825, 826, 827, 828, 829, 830}),
    KNIFE(new int[] {864, 863, 865, 866, 867, 868}),
    THROWING_AXE(new int[] {800, 801, 802, 803, 804, 805}),
	
    //Armour\\
    CHAINBODY(new int[] {1103, 1101, 1105, 1007, 1009, 1011, 1113}),
    FULL_HELM(new int[] {1155, 1153, 1157, 1159, 1161, 1163}),
    KITESHIELD(new int[] {1189, 1191, 1193, 1195, 1197, 1199, 1201}),
    PLATEBODY(new int[] {1117, 1115, 1119, 1125, 1121, 1123, 1127}),
    PLATELEGS(new int[] {1075, 1067, 1069, 1077, 1071, 1073, 1079}),
    SQ_SHIELD(new int[] {1173, 1175, 1177, 1179, 1181, 1183, 1185});
	
    final int[] item;
	
    Item(final int[] item) {
	this.item = item;
    }
    
    public int[] getItem() {
	return item;
    }
}