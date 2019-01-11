#include<stdio.h>
char fuck[100];
int top;
void push(char suck)
{
    if(top==99)
        {
            printf("FFFFUUUUUUUUUCK\n"); return 0;}
    else {
        top++;
            fuck[top]=suck;
    }
}
char pop()
{
    if(top==-1)
        printf("fuck pussy");
    else return fuck[top--];
}
void print()
{
    printf("%d\n",top);
    int i;
    for(i=0;i<=top;i++)
        printf("%c ",fuck[i]);
        printf("\n");


}


int main()
{
    int i,j,k,n;
    scanf("%d",&n);
    char a;
    top=n-1;
    for(i=0; i<n; i++)
    {
        scanf(" %c",&fuck[i]);


    }
print("%d\n",top);
print();
push('c');
print();
pop();
print();
pop();
print();
pop();
print();

print();
}




