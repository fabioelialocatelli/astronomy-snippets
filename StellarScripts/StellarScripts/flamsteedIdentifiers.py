print("String[] flamsteedIdentifiers = {")
for i in range(1, 146):
    if(i < 145):
        print("\"" + str(i) + "\"" + ", ")
    if(i == 145):
        print("\"" + str(i) + "\"")

print("};")
