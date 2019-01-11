#include<stdio.h>
char x[130],y[130];
int top;
void push(char c)
{
    y[++top]=c;

}
char pop()
{
    return y[top--];
}
void print()
{
    int i;
    for(i=0;i<=top;i++)
    {
        printf(" %c",y[i]);
    }
    printf("\n");
}
int main()
{
    int n,i,j,k,l;
    top=-1;
    char c;
    while(scanf("%d",&n)!=EOF)
    {
    for(i=0;i<n;i++)
    {
            top=-1;


        scanf("%s",x);



        for(j=0;j<strlen(x);j++)
        {
            if(x[j]=='('||x[j]=='[')
            {
                push(x[j]);
                print();
                printf("%d\n",top);
            }

            if (x[j]==')')
            {
                 c='(';
            }

             else if(x[j]==']')
              {
                  c='[';
              }
              if(x[j]==')'||x[j]==']')
              {
              if(c==y[top])
                pop();
              else
              {
            printf("No\n");


              break;
              }
              }

        }
        if(top==-1)
            printf("Yes\n");

    }
}
}
