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
int f=0;

        scanf("%s",x);
        if(isEmpty)
        printf("Yes\n");



    else{
            for(j=0;j<strlen(x);j++)
        {

            if(x[j]=='('||x[j]=='[')
            {
                push(x[j]);
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
            /*    if(top==-1)
                {
                    printf("No\n");

                }*/
              if(c==y[top])
                pop();
              else
              {

             f=1;

              break;
              }
              }

        }
        if(top==-1&&f==0)
            printf("Yes\n");
            else
                printf("No\n");

    }
    }
}
}

