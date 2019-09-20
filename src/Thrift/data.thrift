namespace java thrift.generated
namespace py py.thrift.generated

typedef i16 short
typedef i32 int
typedef i64 long
typedef string String
struct Person{
    1:optional String name,
    2:optional int age,
    3:optional bool married
}

exception DataException {
    1:optional String message,
    2:optional String callStack,
    3:optional String date
}
service PersonService{
    Person getPersonByName(1:required String name) throws (1:DataException dataException),
    void setPerson(1:required Person person)
}